package org.people.weijuly.bookstore.operation.query;

import org.people.weijuly.bookstore.model.AuthorsResultModel;
import org.people.weijuly.bookstore.operation.BookStoreOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("searchAuthorsByName")
public class SearchAuthorsByName extends BookStoreOperation {

    @Override
    public void execute() throws Exception {
        System.out.print(helper.getPrompt(name));
        String authorNamePart = scanner.nextLine();
        Map<String, Object> variables = new HashMap<>();
        variables.put("authorNamePart", authorNamePart);
        helper.execute(name, variables, AuthorsResultModel.class);
        scanner.nextLine();
    }
}
