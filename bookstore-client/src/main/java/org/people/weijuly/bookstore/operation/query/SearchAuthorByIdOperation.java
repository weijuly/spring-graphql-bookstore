package org.people.weijuly.bookstore.operation.query;

import org.people.weijuly.bookstore.client.BookStoreGraphQLClient;
import org.people.weijuly.bookstore.model.AuthorResultModel;
import org.people.weijuly.bookstore.operation.BookStoreOperation;
import org.people.weijuly.bookstore.operation.OperationExecutionContext;
import org.people.weijuly.bookstore.util.QueryRenderer;
import org.people.weijuly.bookstore.util.ResourceReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class SearchAuthorByIdOperation implements BookStoreOperation {

    private final String PROMPT_FILE = "display/searchAuthorById.prompt.txt";
    private final String QUERY_TEMPLATE_FILE = "graphql/searchAuthorById.template.graphqls";
    private final String RESPONSE_FIELD = "searchAuthorById";

    private String prompt;

    @Autowired
    private ResourceReader reader;

    @Autowired
    private Scanner scanner;

    @Autowired
    private QueryRenderer renderer;

    @Autowired
    private BookStoreGraphQLClient graphQLClient;

    @PostConstruct
    @Override
    public void init() throws Exception {
        prompt = reader.read(PROMPT_FILE);
    }

    @Override
    public void execute() throws Exception {
        System.out.print(prompt);
        String authorId = scanner.nextLine();
        Map<String, Object> variables = new HashMap<>();
        variables.put("authorId", authorId);
        String query = renderer.render(QUERY_TEMPLATE_FILE, variables);
        graphQLClient.execute(new OperationExecutionContext(query, RESPONSE_FIELD, AuthorResultModel.class));
        scanner.nextLine();
    }
}
