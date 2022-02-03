package org.people.weijuly.bookstore.graphql.impl.mutation;

import org.people.weijuly.bookstore.graphql.BookStoreMutationDataFetchers;
import org.people.weijuly.bookstore.model.AddBookMutationResolver;
import org.people.weijuly.bookstore.model.BookInModel;
import org.people.weijuly.bookstore.model.BookResultModel;
import org.people.weijuly.bookstore.service.BookService;
import org.people.weijuly.bookstore.util.BookStoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Component
public class AddBookMutationResolverImpl implements AddBookMutationResolver {

    private static final Logger logger = LoggerFactory.getLogger(BookStoreMutationDataFetchers.class);

    @Autowired
    private BookService bookService;

    @Override
    public BookResultModel addBook(BookInModel bookIn) throws Exception {
        try {
            return bookService.save(bookIn);
        } catch (BookStoreException e) {
            return e.error();
        } catch (Exception e) {
            return new BookStoreException(e, INTERNAL_SERVER_ERROR).error();
        }
    }
}
