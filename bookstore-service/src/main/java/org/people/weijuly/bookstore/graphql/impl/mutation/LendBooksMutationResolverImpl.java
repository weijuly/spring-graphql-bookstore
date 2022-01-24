package org.people.weijuly.bookstore.graphql.impl.mutation;

import org.people.weijuly.bookstore.data.BookEntity;
import org.people.weijuly.bookstore.data.BookRepository;
import org.people.weijuly.bookstore.data.CustomerRepository;
import org.people.weijuly.bookstore.data.LendingEntity;
import org.people.weijuly.bookstore.data.LendingRepository;
import org.people.weijuly.bookstore.model.CartInModel;
import org.people.weijuly.bookstore.model.CustomerResultModel;
import org.people.weijuly.bookstore.model.LendBooksMutationResolver;
import org.people.weijuly.bookstore.service.CustomerService;
import org.people.weijuly.bookstore.util.BookStoreException;
import org.people.weijuly.bookstore.util.LendingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class LendBooksMutationResolverImpl implements LendBooksMutationResolver {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private LendingRepository lendingRepository;


    @Override
    public CustomerResultModel lendBooks(CartInModel cartIn) throws Exception {
        try {
            validate(cartIn);
            cartIn.getBooks()
                    .forEach(bookId -> lendBook(cartIn.getCustomerId(), bookId));
            return customerService.build(cartIn.getCustomerId());
        } catch (BookStoreException e) {
            return e.error();
        }

    }

    private void lendBook(String customerId, String bookId) {
        updateCopies(bookId);
        lendingRepository.save(lendingEntity(customerId, bookId));
    }

    private void updateCopies(String bookId) {
        bookRepository
                .findById(bookId)
                .map(bookEntity -> {
                    bookEntity.setCopies(bookEntity.getCopies() - 1);
                    return bookRepository.save(bookEntity);
                })
                .orElseThrow(() -> new BookStoreException(
                        String.format("Book with id: %s cannot be updated", bookId),
                        HttpStatus.INTERNAL_SERVER_ERROR
                ));
    }

    private void validate(CartInModel cartIn) {
        if (cartIn.getBooks().isEmpty()) {
            throw new BookStoreException("Book list is empty", HttpStatus.BAD_REQUEST);
        }
        customerRepository
                .findById(cartIn.getCustomerId())
                .orElseThrow(() -> new BookStoreException(
                        String.format("Customer with id: %s cannot be found", cartIn.getCustomerId()),
                        HttpStatus.BAD_REQUEST
                ));
        for (String bookId : cartIn.getBooks()) {
            BookEntity bookEntity = bookRepository
                    .findById(bookId)
                    .orElseThrow(() -> new BookStoreException(
                            String.format("Book with id: %s cannot be found", cartIn.getCustomerId()),
                            HttpStatus.BAD_REQUEST
                    ));
            if (bookEntity.getCopies() == 0) {
                throw new BookStoreException(
                        String.format("Book with id: %s is currently unavailable", cartIn.getCustomerId()),
                        HttpStatus.BAD_REQUEST
                );
            }
        }
    }

    private LendingEntity lendingEntity(String customerId, String bookId) {
        LendingEntity lendingEntity = new LendingEntity();
        lendingEntity.setCustomerId(customerId);
        lendingEntity.setBookId(bookId);
        lendingEntity.setStatus(LendingStatus.ACTIVE);
        return lendingEntity;
    }
}
