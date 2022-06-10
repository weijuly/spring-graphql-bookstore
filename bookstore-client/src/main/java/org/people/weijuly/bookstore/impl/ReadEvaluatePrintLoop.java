package org.people.weijuly.bookstore.impl;

import org.people.weijuly.bookstore.operation.BookStoreOperation;
import org.people.weijuly.bookstore.operation.UnknownOperation;
import org.people.weijuly.bookstore.util.BookStoreOperationOption;
import org.people.weijuly.bookstore.util.ResourceReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.people.weijuly.bookstore.util.BookStoreConstants.unknownOperation;

@Component
public class ReadEvaluatePrintLoop {

    private final Map<String, BookStoreOperation> commands = new HashMap<>();
    @Autowired
    private ResourceReader reader;
    @Autowired
    private Scanner scanner;
    private String menu;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private UnknownOperation unknown;

    @PostConstruct
    public void init() throws Exception {
        for(BookStoreOperationOption option: BookStoreOperationOption.values()) {
            commands.put(option.optionCode, context.getBean(option.name(), BookStoreOperation.class));
        }
        menu = reader.read("display/mainMenu.txt");
    }


    public void loop() {
        do {
            try {
                System.out.print(menu);
                String selection = scanner.nextLine();
                if (selection.equals(unknownOperation)) {
                    return;
                }
                commands.getOrDefault(selection, unknown).execute();

            } catch (Exception e) {
                System.out.println("Something went wrong");
            }
        } while (true);
    }
}
