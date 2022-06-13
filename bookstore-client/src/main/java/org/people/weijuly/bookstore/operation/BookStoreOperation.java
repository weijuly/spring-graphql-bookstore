package org.people.weijuly.bookstore.operation;

import org.people.weijuly.bookstore.operation.query.OperationHelper;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

public abstract class BookStoreOperation implements BeanNameAware {

    protected String name = null;

    @Autowired
    protected OperationHelper helper;

    @Autowired
    protected Scanner scanner;


    @Override
    public void setBeanName(String name) {
        this.name = name;
    }

    public abstract void execute() throws Exception;

}
