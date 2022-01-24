package org.people.weijuly.bookstore.graphql.impl.query;

import org.people.weijuly.bookstore.model.BooksResultModel;
import org.people.weijuly.bookstore.model.SearchBooksByNameQueryResolver;
import org.people.weijuly.bookstore.service.BookService;
import org.people.weijuly.bookstore.util.BookStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Component
public class SearchBooksByNameQueryResolverImpl implements SearchBooksByNameQueryResolver {

    @Autowired
    BookService bookService;

    @Override
    public BooksResultModel searchBooksByName(String name) throws Exception {
        try {
            return bookService.searchBooksByName(name);
        } catch (BookStoreException e) {
            return e.error();
        } catch (Exception e) {
            return new BookStoreException(e, INTERNAL_SERVER_ERROR).error();
        }
    }
}
