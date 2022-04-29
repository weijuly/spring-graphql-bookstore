package org.people.weijuly.bookstore.operation.query;


import org.people.weijuly.bookstore.client.BookStoreGraphQLClient;
import org.people.weijuly.bookstore.operation.OperationExecutionContext;
import org.people.weijuly.bookstore.util.QueryRenderer;
import org.people.weijuly.bookstore.util.ResourceReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class OperationHelper {

    @Autowired
    ResourceReader reader;

    @Autowired
    QueryRenderer renderer;

    @Autowired
    BookStoreGraphQLClient graphQLClient;

    public String getPrompt(String promptFile) throws IOException {
        return reader.read(promptFile);
    }

    public void execute(String templateFile, Map<String, Object> variables, String responseField, Class<?> type)
            throws Exception {
        graphQLClient.execute(new OperationExecutionContext(
                renderer.render(templateFile, variables),
                responseField,
                type
        ));
    }
}
