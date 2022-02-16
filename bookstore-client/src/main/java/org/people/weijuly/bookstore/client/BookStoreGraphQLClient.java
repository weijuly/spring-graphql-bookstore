package org.people.weijuly.bookstore.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
import graphql.kickstart.spring.webclient.boot.GraphQLResponse;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;
import org.people.weijuly.bookstore.model.AuthorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookStoreGraphQLClient {

    @Autowired
    private GraphQLWebClient graphQLWebClient;

    @Autowired
    private ObjectMapper mapper;

    public String execute(String query) throws Exception {
        // TODO: Find out how to handle ADT ( Algebriac Data Types ) in GraphQLResponse
        GraphQLRequest request = GraphQLRequest.builder().query(query).build();
        GraphQLResponse response = graphQLWebClient.post(request).block();
        return mapper.writeValueAsString(response.get("searchAuthorById", AuthorModel.class));
    }
}
