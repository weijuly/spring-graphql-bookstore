package org.people.weijuly.bookstore.impl.query;

import org.people.weijuly.bookstore.impl.CommandExecutor;
import org.springframework.stereotype.Component;

@Component
public class UnknownSelectionExecutor implements CommandExecutor {

    @Override
    public void execute() throws Exception {
        System.out.println("Selection not in list, please try again...");
    }
}
