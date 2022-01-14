package org.people.weijuly.bookstore.graphql;

import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.TypeResolver;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeRuntimeWiring;
import org.people.weijuly.bookstore.graphql.impl.type.AddAuthorResultResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;

import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;
import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;
import static org.springframework.util.StreamUtils.copyToString;

@Component
public class GraphQLProvider {

	private static final Logger logger = LoggerFactory.getLogger(GraphQLProvider.class);

	private GraphQL graphQL;

	private GraphQLSchema schema;

	@Value("${graphql.specification.file}")
	private String specFilePath;

	@Autowired
	private GraphQLDataFetchers fetchers;

	@Autowired
	private GraphQLTypeResolvers resolvers;

	@Bean
	public GraphQLSchema schema() {
		return schema;
	}

	@PostConstruct
	public void init() throws IOException {
		schema = schema(specification());
		logger.info("Schema constructed");
	}

	private GraphQLSchema schema(String spec) {
		return new SchemaGenerator()
				.makeExecutableSchema(
						new SchemaParser().parse(spec),
						wiring()
				);
	}

	private RuntimeWiring wiring() {
		return newRuntimeWiring()
				.type("Query", typeWiring -> typeWiring
						.dataFetcher("searchBookById", fetchers.searchBookById())
						.dataFetcher("authors", fetchers.authors()))
				.type("Mutation", typeWiring -> typeWiring
						.dataFetcher("addAuthor", fetchers.addAuthor()))
				.type("AddAuthorResult", typeWiring -> typeWiring
						.typeResolver(resolvers.addAuthorResultResolver()))
				.build();


//		return newRuntimeWiring()
//				.type(fetcher("Query", "searchBookById", fetchers.searchBookById()))
//				.type(fetcher("Query", "authors", fetchers.authors()))
//				.type(fetcher("Mutation", "addAuthor", fetchers.addAuthor()))
//				.type(resolver("AddAuthorResult", resolvers.addAuthorResultResolver()))
//				.build();
	}

	private String specification() throws IOException {
		return copyToString(new ClassPathResource(specFilePath).getInputStream(), Charset.defaultCharset());
	}

//	private TypeRuntimeWiring.Builder fetcher(String typeName, String fieldName, DataFetcher fetcher) {
//		return newTypeWiring(typeName).dataFetcher(fieldName, fetcher);
//	}
//
//	private TypeRuntimeWiring.Builder resolver(String typeName, TypeResolver resolver) {
//		return newTypeWiring(typeName).typeResolver(resolver);
//
//	}
}
