package org.people.weijuly.bookstore.operation.query;

import org.people.weijuly.bookstore.client.BookStoreGraphQLClient;
import org.people.weijuly.bookstore.operation.BookStoreOperation;
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
        prompt = reader.read("display/searchAuthorById.prompt.txt");
    }

    @Override
    public void execute() throws Exception {
        System.out.println(prompt);
        String authorId = scanner.nextLine();
        Map<String, Object> variables = new HashMap<>();
        variables.put("authorId", authorId);
        String query = renderer.render("graphql/searchAuthorById.template.graphqls", variables);
        System.out.println(query);
        String result = graphQLClient.execute(query);
        System.out.println(result);
    }
}
