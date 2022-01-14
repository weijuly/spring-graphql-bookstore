package org.people.weijuly.bookstore.util;

import org.people.weijuly.bookstore.model.BookStoreErrorModel;

public class ErrorUtil {

    public static BookStoreErrorModel bookStoreError() {
        BookStoreErrorModel error = new BookStoreErrorModel();
        error.setMessage("Internal Server Error occurred while processing the request");
        error.setStatus(500);
        return error;
    }
}
