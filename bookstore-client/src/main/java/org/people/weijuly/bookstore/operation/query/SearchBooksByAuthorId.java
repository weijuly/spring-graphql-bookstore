package org.people.weijuly.bookstore.operation.query;

import org.people.weijuly.bookstore.model.BookResultModel;
import org.people.weijuly.bookstore.model.BooksResultModel;
import org.people.weijuly.bookstore.operation.BookStoreOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("searchBooksByAuthorId")
public class SearchBooksByAuthorId extends BookStoreOperation {
    @Override
    public void execute() throws Exception {
        System.out.print(helper.getPrompt(name));
        String authorId = scanner.nextLine();
        Map<String, Object> variables = new HashMap<>();
        variables.put("authorId", authorId);
        helper.execute(name, variables, BooksResultModel.class);
        scanner.nextLine();
    }
}
