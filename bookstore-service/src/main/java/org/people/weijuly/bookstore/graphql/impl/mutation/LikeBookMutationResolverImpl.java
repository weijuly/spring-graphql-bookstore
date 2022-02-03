package org.people.weijuly.bookstore.graphql.impl.mutation;

import org.people.weijuly.bookstore.model.CustomerResultModel;
import org.people.weijuly.bookstore.model.LikeBookMutationResolver;
import org.people.weijuly.bookstore.model.LikeInModel;
import org.people.weijuly.bookstore.service.BookService;
import org.people.weijuly.bookstore.service.CustomerService;
import org.people.weijuly.bookstore.service.LikeService;
import org.people.weijuly.bookstore.util.BookStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class LikeBookMutationResolverImpl implements LikeBookMutationResolver {

    @Autowired
    CustomerService customerService;

    @Autowired
    BookService bookService;

    @Autowired
    LikeService likeService;

    @Override
    public CustomerResultModel likeBook(LikeInModel likeIn) throws Exception {
        try {
            validate(likeIn);
            likeService.likeBook(likeIn.getCustomerId(), likeIn.getBookId());
            return customerService.build(likeIn.getCustomerId());
        } catch (BookStoreException e) {
            return e.error();
        }
    }

    private void validate(LikeInModel likeIn) {
        String customerId = likeIn.getCustomerId();
        String bookId = likeIn.getBookId();

        if (!bookService.exists(bookId)) {
            throw new BookStoreException(
                    String.format("Book with id: %s cannot be found", bookId),
                    BAD_REQUEST
            );
        }

        if (!customerService.exists(customerId)) {
            throw new BookStoreException(
                    String.format("Customer with id: %s cannot be found", customerId),
                    BAD_REQUEST
            );
        }

        if (!customerService.didPurchaseOrLend(customerId, bookId)) {
            throw new BookStoreException(
                    String.format("Customer id: %s did not purchase or lend book id: %s", customerId, bookId),
                    BAD_REQUEST
            );
        }
    }
}
