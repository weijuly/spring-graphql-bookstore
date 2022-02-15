package org.people.weijuly.bookstore.impl;

import org.people.weijuly.bookstore.impl.query.SearchAuthorById;
import org.people.weijuly.bookstore.impl.query.UnknownSelectionExecutor;
import org.people.weijuly.bookstore.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class ReadEvaluatePrintLoop {

    @Autowired
    private ResourceUtil resourceUtil;

    private Map<String, CommandExecutor> commands = new HashMap<>();

    @Autowired
    private SearchAuthorById searchAuthorById;

    @Autowired
    private UnknownSelectionExecutor unknownSelectionExecutor;

    @PostConstruct
    public void init() {
        commands.put("a", searchAuthorById);
    }


    public void loop() {
        do {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print(resourceUtil.getResourceAsString("main-menu.txt"));
                String selection = scanner.nextLine();
                if (selection.equals("z")) {
                    return;
                }
                commands.getOrDefault(selection, unknownSelectionExecutor).execute();

            } catch (Exception e) {
                System.out.println("Something went wrong");
            }
        } while (true);
    }
}
