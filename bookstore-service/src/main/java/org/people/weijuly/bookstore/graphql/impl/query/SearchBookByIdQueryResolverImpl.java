package org.people.weijuly.bookstore.graphql.impl.query;

import org.people.weijuly.bookstore.model.BookResultModel;
import org.people.weijuly.bookstore.model.SearchBookByIdQueryResolver;
import org.people.weijuly.bookstore.service.BookService;
import org.people.weijuly.bookstore.util.BookStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Component
public class SearchBookByIdQueryResolverImpl implements SearchBookByIdQueryResolver {

    @Autowired
    private BookService bookService;

    @Override
    public BookResultModel searchBookById(String bookId) throws Exception {
        try {
            return bookService.build(bookId);
        } catch (BookStoreException e) {
            return e.error();
        } catch (Exception e) {
            return new BookStoreException(e, INTERNAL_SERVER_ERROR).error();
        }
    }
}
