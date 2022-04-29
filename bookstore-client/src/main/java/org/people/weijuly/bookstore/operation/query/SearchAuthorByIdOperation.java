package org.people.weijuly.bookstore.operation.query;

import org.people.weijuly.bookstore.model.AuthorResultModel;
import org.people.weijuly.bookstore.operation.BookStoreOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class SearchAuthorByIdOperation implements BookStoreOperation {

    private final String PROMPT_FILE = "display/searchAuthorById.prompt.txt";
    private final String QUERY_TEMPLATE_FILE = "graphql/searchAuthorById.template.graphqls";
    private final String RESPONSE_FIELD = "searchAuthorById";

    @Autowired
    private Scanner scanner;

    @Autowired
    private OperationHelper helper;


    @Override
    public void execute() throws Exception {

        System.out.print(helper.getPrompt(PROMPT_FILE));
        String authorId = scanner.nextLine();
        Map<String, Object> variables = new HashMap<>();
        variables.put("authorId", authorId);
        helper.execute(QUERY_TEMPLATE_FILE, variables, RESPONSE_FIELD, AuthorResultModel.class);
        scanner.nextLine();
    }
}
