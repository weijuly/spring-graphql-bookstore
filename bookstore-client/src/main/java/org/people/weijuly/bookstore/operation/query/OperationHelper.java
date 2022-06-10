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

    public String getPrompt(String name) throws IOException {
        return reader.read(promptFile(name));
    }

    public void execute(String name, Map<String, Object> variables, Class<?> type)
            throws Exception {
        graphQLClient.execute(new OperationExecutionContext(
                renderer.render(templateFile(name), variables),
                name,
                type
        ));
    }

    private String promptFile(String name) {
        return String.format("display/%s.prompt.txt", name);
    }

    private String templateFile(String name) {
        return String.format("graphql/%s.template.graphqls", name);
    }
}
