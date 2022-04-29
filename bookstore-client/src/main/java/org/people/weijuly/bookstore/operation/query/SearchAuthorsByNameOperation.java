package org.people.weijuly.bookstore.operation.query;

import org.people.weijuly.bookstore.model.AuthorsResultModel;
import org.people.weijuly.bookstore.operation.BookStoreOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class SearchAuthorsByNameOperation implements BookStoreOperation {

    private final String PROMPT_FILE = "display/searchAuthorsByName.prompt.txt";
    private final String QUERY_TEMPLATE_FILE = "graphql/searchAuthorsByName.template.graphqls";
    private final String RESPONSE_FIELD = "searchAuthorsByName";

    @Autowired
    private Scanner scanner;

    @Autowired
    private OperationHelper helper;

    @Override
    public void execute() throws Exception {
        System.out.print(helper.getPrompt(PROMPT_FILE));
        String authorNamePart = scanner.nextLine();
        Map<String, Object> variables = new HashMap<>();
        variables.put("authorNamePart", authorNamePart);
        helper.execute(QUERY_TEMPLATE_FILE, variables, RESPONSE_FIELD, AuthorsResultModel.class);
        scanner.nextLine();
    }
}
