package org.people.weijuly.bookstore.graphql.impl.query;

import org.people.weijuly.bookstore.model.BooksResultModel;
import org.people.weijuly.bookstore.model.SearchBooksByAuthorNameQueryResolver;
import org.people.weijuly.bookstore.service.BookService;
import org.people.weijuly.bookstore.util.BookStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Component
public class SearchBooksByAuthorNameQueryResolverImpl implements SearchBooksByAuthorNameQueryResolver {

    @Autowired
    BookService bookService;

    @Override
    public BooksResultModel searchBooksByAuthorName(String name) throws Exception {
        try {
            return bookService.searchBooksByAuthorName(name);
        } catch (BookStoreException e) {
            return e.error();
        } catch (Exception e) {
            return new BookStoreException(e, INTERNAL_SERVER_ERROR).error();
        }
    }
}
