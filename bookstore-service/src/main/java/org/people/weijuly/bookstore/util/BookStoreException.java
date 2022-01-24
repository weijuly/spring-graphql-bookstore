package org.people.weijuly.bookstore.util;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.people.weijuly.bookstore.model.BookStoreErrorModel;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class BookStoreException extends RuntimeException {

    private final String message;
    private final HttpStatus status;

    public BookStoreException() {
        message = "Something went wrong.";
        status = INTERNAL_SERVER_ERROR;
    }

    public BookStoreException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public BookStoreException(Exception e, HttpStatus status) {
        this.message = String.format("[%s: %s], caused by: %s",
                e.getClass().getName(), e.getMessage(), ExceptionUtils.getRootCauseMessage(e));
        this.status = status;
    }

    public BookStoreErrorModel error() {
        BookStoreErrorModel error = new BookStoreErrorModel();
        error.setMessage(String.format("%s: %s", status.name(), message));
        error.setStatus(status.value());
        return error;
    }

    public void log() {

    }

}
