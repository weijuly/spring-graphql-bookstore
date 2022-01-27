package org.people.weijuly.bookstore.client;

import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
import graphql.kickstart.spring.webclient.boot.GraphQLResponse;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;
import org.people.weijuly.bookstore.model.BookModel;
import org.people.weijuly.bookstore.model.BooksModel;
import org.people.weijuly.bookstore.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SampleClient {

    @Autowired
    private GraphQLWebClient graphQLWebClient;

    @Autowired
    private ResourceUtil resourceUtil;

    public void call() {
        try {
            String query = resourceUtil.getResourceAsString("query/searchBookByTag.graphqls");
            GraphQLRequest request = GraphQLRequest.builder().query(query).build();
            GraphQLResponse response = graphQLWebClient.post(request).block();
            BooksModel booksModel = response.get("searchBooksByTag", BooksModel.class);
            for (BookModel bookModel : booksModel.getBooks()) {
                System.out.println(bookModel.getId());
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
