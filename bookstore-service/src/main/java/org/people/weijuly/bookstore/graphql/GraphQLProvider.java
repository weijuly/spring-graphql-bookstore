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
import static org.people.weijuly.bookstore.util.BookStoreConstants.AuthorResultType;
import static org.people.weijuly.bookstore.util.BookStoreConstants.AuthorsResultType;
import static org.people.weijuly.bookstore.util.BookStoreConstants.BookResultType;
import static org.people.weijuly.bookstore.util.BookStoreConstants.BooksResultType;
import static org.people.weijuly.bookstore.util.BookStoreConstants.CustomerResultType;
import static org.people.weijuly.bookstore.util.BookStoreConstants.CustomersResultType;
import static org.people.weijuly.bookstore.util.BookStoreConstants.MutationType;
import static org.people.weijuly.bookstore.util.BookStoreConstants.QueryType;
import static org.people.weijuly.bookstore.util.BookStoreConstants.addBookMutation;
import static org.people.weijuly.bookstore.util.BookStoreConstants.addCustomerMutation;
import static org.people.weijuly.bookstore.util.BookStoreConstants.lendBooksMutation;
import static org.people.weijuly.bookstore.util.BookStoreConstants.likeBookMutation;
import static org.people.weijuly.bookstore.util.BookStoreConstants.purchaseBooksMutation;
import static org.people.weijuly.bookstore.util.BookStoreConstants.returnBooksMutation;
import static org.people.weijuly.bookstore.util.BookStoreConstants.searchAuthorByIdQuery;
import static org.people.weijuly.bookstore.util.BookStoreConstants.searchAuthorsByNameQuery;
import static org.people.weijuly.bookstore.util.BookStoreConstants.searchBookByIdQuery;
import static org.people.weijuly.bookstore.util.BookStoreConstants.searchBooksByAuthorIdQuery;
import static org.people.weijuly.bookstore.util.BookStoreConstants.searchBooksByAuthorNameQuery;
import static org.people.weijuly.bookstore.util.BookStoreConstants.searchBooksByNameQuery;
import static org.people.weijuly.bookstore.util.BookStoreConstants.searchBooksByTagQuery;
import static org.people.weijuly.bookstore.util.BookStoreConstants.searchBooksByYearQuery;
import static org.people.weijuly.bookstore.util.BookStoreConstants.searchCustomerByIdQuery;
import static org.people.weijuly.bookstore.util.BookStoreConstants.searchCustomersByBookLendQuery;
import static org.people.weijuly.bookstore.util.BookStoreConstants.searchCustomersByBookLikedQuery;
import static org.people.weijuly.bookstore.util.BookStoreConstants.searchCustomersByBookPurchasedQuery;
import static org.people.weijuly.bookstore.util.BookStoreConstants.searchCustomersByNameQuery;
import static org.springframework.util.StreamUtils.copyToString;

@Component
public class GraphQLProvider {

    private static final Logger logger = LoggerFactory.getLogger(GraphQLProvider.class);

    private GraphQL graphQL;

    private GraphQLSchema schema;

    @Value("${graphql.specification.file}")
    private String specFilePath;

    @Autowired
    private BookStoreQueryDataFetchers queryDataFetchers;

    @Autowired
    private BookStoreMutationDataFetchers mutationDataFetchers;

    @Autowired
    private BookStoreTypeResolvers typeResolvers;

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
                        .dataFetcher(searchAuthorByIdQuery, queryDataFetchers.searchAuthorById())
                        .dataFetcher(searchAuthorsByNameQuery, queryDataFetchers.searchAuthorsByName())
                        .dataFetcher(searchBookByIdQuery, queryDataFetchers.searchBookById())
                        .dataFetcher(searchBooksByAuthorIdQuery, queryDataFetchers.searchBooksByAuthorId())
                        .dataFetcher(searchBooksByAuthorNameQuery, queryDataFetchers.searchBooksByAuthorName())
                        .dataFetcher(searchBooksByNameQuery, queryDataFetchers.searchBooksByName())
                        .dataFetcher(searchBooksByTagQuery, queryDataFetchers.searchBooksByTag())
                        .dataFetcher(searchBooksByYearQuery, queryDataFetchers.searchBooksByYear())
                        .dataFetcher(searchCustomerByIdQuery, queryDataFetchers.searchCustomerById())
                        .dataFetcher(searchCustomersByBookLendQuery, queryDataFetchers.searchCustomersByBookLend())
                        .dataFetcher(searchCustomersByBookLikedQuery, queryDataFetchers.searchCustomersByBookLiked())
                        .dataFetcher(searchCustomersByBookPurchasedQuery, queryDataFetchers.searchCustomersByBookPurchased())
                        .dataFetcher(searchCustomersByNameQuery, queryDataFetchers.searchCustomersByName()))
                .type(MutationType, typeWiring -> typeWiring
                        .dataFetcher(addCustomerMutation, mutationDataFetchers.addCustomer())
                        .dataFetcher(purchaseBooksMutation, mutationDataFetchers.purchaseBooks())
                        .dataFetcher(lendBooksMutation, mutationDataFetchers.lendBooks())
                        .dataFetcher(returnBooksMutation, mutationDataFetchers.returnBooks())
                        .dataFetcher(likeBookMutation, mutationDataFetchers.likeBook())
                        .dataFetcher(addBookMutation, mutationDataFetchers.addBook()))
                .type(AuthorResultType, typeWiring -> typeWiring
                        .typeResolver(typeResolvers.authorResultTypeResolver()))
                .type(AuthorsResultType, typeWiring -> typeWiring
                        .typeResolver(typeResolvers.authorsResultTypeResolver()))
                .type(BookResultType, typeWiring -> typeWiring
                        .typeResolver(typeResolvers.bookResultTypeResolver()))
                .type(BooksResultType, typeWiring -> typeWiring
                        .typeResolver(typeResolvers.booksResultTypeResolver()))
                .type(CustomerResultType, typeWiring -> typeWiring
                        .typeResolver(typeResolvers.customerResultTypeResolver()))
                .type(CustomersResultType, typeWiring -> typeWiring
                        .typeResolver(typeResolvers.customersResultTypeResolver()))
                .build();
    }

    private String specification() throws IOException {
        return copyToString(new ClassPathResource(specFilePath).getInputStream(), Charset.defaultCharset());
    }
}
