package org.people.weijuly.bookstore.application;


import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;
import org.junit.jupiter.api.Test;
import org.people.weijuly.bookstore.BookStoreApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = {BookStoreApplication.class}
)
@ActiveProfiles("test")
public class ApplicationTest {

    @Autowired
    GraphQLWebClient graphQLWebClient;


    @Test
    public void test() {
        WebClient client = WebClient
                .builder()
                .baseUrl("http://localhost:8080/graphql")
                .build();

        graphQLWebClient = GraphQLWebClient.newInstance(client, new ObjectMapper());

        System.out.println("test" + graphQLWebClient);
        GraphQLRequest request = GraphQLRequest.builder().query("ja ja").build();
        graphQLWebClient.post(request)
                .blockOptional(Duration.ofSeconds(2L))
                .map(response -> response.getErrors().toString())
                .orElseThrow(() -> new RuntimeException("ka ka"));
    }
}
