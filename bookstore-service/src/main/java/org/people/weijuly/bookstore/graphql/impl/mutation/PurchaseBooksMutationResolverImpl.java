package org.people.weijuly.bookstore.graphql.impl.mutation;

import org.people.weijuly.bookstore.data.BookEntity;
import org.people.weijuly.bookstore.data.BookRepository;
import org.people.weijuly.bookstore.data.CustomerRepository;
import org.people.weijuly.bookstore.data.PurchaseEntity;
import org.people.weijuly.bookstore.data.PurchaseRepository;
import org.people.weijuly.bookstore.model.CartInModel;
import org.people.weijuly.bookstore.model.CustomerResultModel;
import org.people.weijuly.bookstore.model.PurchaseBooksMutationResolver;
import org.people.weijuly.bookstore.service.CustomerService;
import org.people.weijuly.bookstore.util.BookStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class PurchaseBooksMutationResolverImpl implements PurchaseBooksMutationResolver {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    CustomerService customerService;

    @Override
    public CustomerResultModel purchaseBooks(CartInModel cartIn) throws Exception {
        try {
            validate(cartIn);
            cartIn.getBooks()
                    .forEach(bookId -> purchaseBook(cartIn.getCustomerId(), bookId));
            return customerService.build(cartIn.getCustomerId());
        } catch (BookStoreException e) {
            return e.error();
        }
    }

    private void purchaseBook(String customerId, String bookId) {
        updateCopies(bookId);
        purchaseRepository.save(purchaseEntity(bookId, customerId));
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

    private PurchaseEntity purchaseEntity(String bookId, String customerId) {
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setBookId(bookId);
        purchaseEntity.setCustomerId(customerId);
        return purchaseEntity;
    }
}
