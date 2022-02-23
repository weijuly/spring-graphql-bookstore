package org.people.weijuly.bookstore.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
import graphql.kickstart.spring.webclient.boot.GraphQLResponse;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;
import io.netty.util.internal.StringUtil;
import org.people.weijuly.bookstore.operation.OperationExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookStoreGraphQLClient {

    @Autowired
    private GraphQLWebClient graphQLWebClient;

    @Autowired
    private ObjectMapper mapper;

    public void execute(OperationExecutionContext context) throws Exception {
        System.out.printf(">>> Executing Query:\n%s\n", context.getQuery());
        GraphQLRequest request = GraphQLRequest.builder().query(context.getQuery()).build();
        graphQLWebClient
                .post(request)
                .blockOptional()
                .map(response -> display(response, context.getField(), context.getType()))
                .orElseThrow(() -> new RuntimeException("exception")); // TODO: rework exception handling
    }

    private String display(GraphQLResponse response, String fieldName, Class<?> type) {
        try {
            String content = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(response.get(fieldName, type));
            System.out.printf(">>> Server response:\n%s\n", content);
        } catch (Exception e) {
            System.out.printf(">>> Cannot parse response from server, error: %s\n", e.getMessage());
        }
        return StringUtil.EMPTY_STRING; // for map continuation
    }

}
