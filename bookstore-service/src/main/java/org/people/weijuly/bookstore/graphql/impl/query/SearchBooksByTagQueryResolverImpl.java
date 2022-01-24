package org.people.weijuly.bookstore.graphql.impl.query;

import org.people.weijuly.bookstore.model.BooksResultModel;
import org.people.weijuly.bookstore.model.SearchBooksByTagQueryResolver;
import org.people.weijuly.bookstore.model.TagModel;
import org.people.weijuly.bookstore.service.BookService;
import org.people.weijuly.bookstore.util.BookStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Component
public class SearchBooksByTagQueryResolverImpl implements SearchBooksByTagQueryResolver {

    @Autowired
    BookService bookService;


    @Override
    public BooksResultModel searchBooksByTag(TagModel tag) throws Exception {
        try {
            return bookService.searchBooksByTag(tag);
        } catch (BookStoreException e) {
            return e.error();
        } catch (Exception e) {
            return new BookStoreException(e, INTERNAL_SERVER_ERROR).error();
        }
    }
}
