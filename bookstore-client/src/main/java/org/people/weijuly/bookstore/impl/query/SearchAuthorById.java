package org.people.weijuly.bookstore.impl.query;

import org.people.weijuly.bookstore.impl.CommandExecutor;
import org.people.weijuly.bookstore.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SearchAuthorById implements CommandExecutor {

    @Autowired
    ResourceUtil resourceUtil;

    @Override
    public void execute() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println(resourceUtil.getResourceAsString("searchAuthorById.txt"));
        String authorId = scanner.nextLine();
        System.out.println(authorId);
    }
}
