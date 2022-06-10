package org.people.weijuly.bookstore.operation;

import org.springframework.beans.factory.BeanNameAware;

public abstract class BookStoreOperation implements BeanNameAware {

    protected String name = null;

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }

    public abstract void execute() throws Exception;

}
