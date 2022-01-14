package org.people.weijuly.bookstore.graphql;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
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
import static org.people.weijuly.bookstore.util.BookStoreConstants.AddAuthorResultType;
import static org.people.weijuly.bookstore.util.BookStoreConstants.AddBookResultType;
import static org.people.weijuly.bookstore.util.BookStoreConstants.MutationType;
import static org.people.weijuly.bookstore.util.BookStoreConstants.QueryType;
import static org.people.weijuly.bookstore.util.BookStoreConstants.addAuthorMutation;
import static org.people.weijuly.bookstore.util.BookStoreConstants.addBookMutation;
import static org.people.weijuly.bookstore.util.BookStoreConstants.searchBookByAuthorQuery;
import static org.people.weijuly.bookstore.util.BookStoreConstants.searchBookByIdQuery;
import static org.springframework.util.StreamUtils.copyToString;

@Component
public class GraphQLProvider {

    private static final Logger logger = LoggerFactory.getLogger(GraphQLProvider.class);

    private GraphQL graphQL;

    private GraphQLSchema schema;

    @Value("${graphql.specification.file}")
    private String specFilePath;

    @Autowired
    private BookStoreDataFetchers fetchers;

    @Autowired
    private BookStoreTypeResolvers resolvers;

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
                .type(QueryType, typeWiring -> typeWiring
                        .dataFetcher(searchBookByAuthorQuery, fetchers.searchBookByAuthor())
                        .dataFetcher(searchBookByIdQuery, fetchers.searchBookById())
                        .dataFetcher("authors", fetchers.authors()))
                .type(MutationType, typeWiring -> typeWiring
                        .dataFetcher(addBookMutation, fetchers.addBook()))
                .type(AddBookResultType, typeWiring -> typeWiring
                        .typeResolver(resolvers.addBookResultResovler()))
                .build();
    }

    private String specification() throws IOException {
        return copyToString(new ClassPathResource(specFilePath).getInputStream(), Charset.defaultCharset());
    }
}
