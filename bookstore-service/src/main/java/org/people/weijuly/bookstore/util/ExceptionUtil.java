package org.people.weijuly.bookstore.util;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.people.weijuly.bookstore.model.BookStoreErrorModel;

public class ExceptionUtil {

    public static String format(Exception e) {
        return String.format("[%s: %s], caused by: %s",
                e.getClass().getName(), e.getMessage(), ExceptionUtils.getRootCauseMessage(e));
    }

    public static BookStoreErrorModel error(String message, Integer status) {
        BookStoreErrorModel errorModel = new BookStoreErrorModel();
        errorModel.setMessage(message);
        errorModel.setStatus(status);
        return errorModel;
    }

    public static BookStoreErrorModel error(Exception e, Integer status) {
        BookStoreErrorModel errorModel = new BookStoreErrorModel();
        errorModel.setMessage(format(e));
        errorModel.setStatus(status);
        return errorModel;
    }
}
