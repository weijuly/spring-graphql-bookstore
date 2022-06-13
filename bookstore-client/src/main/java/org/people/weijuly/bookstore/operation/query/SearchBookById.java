package org.people.weijuly.bookstore.operation.query;

import org.people.weijuly.bookstore.model.BookResultModel;
import org.people.weijuly.bookstore.operation.BookStoreOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("searchBookById")
public class SearchBookById extends BookStoreOperation {
    @Override
    public void execute() throws Exception {
        System.out.print(helper.getPrompt(name));
        String bookId = scanner.nextLine();
        Map<String, Object> variables = new HashMap<>();
        variables.put("bookId", bookId);
        helper.execute(name, variables, BookResultModel.class);
        scanner.nextLine();
    }
}
