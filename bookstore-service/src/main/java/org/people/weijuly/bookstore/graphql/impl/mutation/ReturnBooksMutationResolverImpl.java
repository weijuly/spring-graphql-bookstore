package org.people.weijuly.bookstore.graphql.impl.mutation;

import org.people.weijuly.bookstore.data.BookRepository;
import org.people.weijuly.bookstore.data.CustomerRepository;
import org.people.weijuly.bookstore.data.LendingEntity;
import org.people.weijuly.bookstore.data.LendingRepository;
import org.people.weijuly.bookstore.model.CartInModel;
import org.people.weijuly.bookstore.model.CustomerResultModel;
import org.people.weijuly.bookstore.model.ReturnBooksMutationResolver;
import org.people.weijuly.bookstore.service.CustomerService;
import org.people.weijuly.bookstore.util.BookStoreException;
import org.people.weijuly.bookstore.util.LendingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReturnBooksMutationResolverImpl implements ReturnBooksMutationResolver {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private LendingRepository lendingRepository;


    @Override
    public CustomerResultModel returnBooks(CartInModel cartIn) throws Exception {
        try {
            validate(cartIn);
            cartIn.getBooks()
                    .forEach(bookId -> returnBook(cartIn.getCustomerId(), bookId));
            return customerService.build(cartIn.getCustomerId());
        } catch (BookStoreException e) {
            return e.error();
        }
    }

    private void returnBook(String customerId, String bookId) {
        lendingRepository
                .findByCustomerIdAndBookIdAndStatus(customerId, bookId, LendingStatus.ACTIVE)
                .ifPresent(lendingEntity -> {
                    lendingEntity.setStatus(LendingStatus.RETURNED);
                    lendingRepository.save(lendingEntity);
                });
    }

    private void validate(CartInModel cartIn) {
        if (cartIn.getBooks().isEmpty()) {
            throw new BookStoreException("Book list is empty", HttpStatus.BAD_REQUEST);
        }

        if (!customerRepository.existsById(cartIn.getCustomerId())) {
            throw new BookStoreException(
                    String.format("Customer with id: %s cannot be found", cartIn.getCustomerId()),
                    HttpStatus.BAD_REQUEST
            );
        }

        List<String> lendBookIds = lendingRepository
                .findByCustomerId(cartIn.getCustomerId())
                .stream()
                .map(LendingEntity::getBookId)
                .collect(Collectors.toList());

        for (String bookId : cartIn.getBooks()) {
            if (!bookRepository.existsById(bookId)) {
                throw new BookStoreException(
                        String.format("Book with id: %s cannot be found", cartIn.getCustomerId()),
                        HttpStatus.BAD_REQUEST
                );
            }
            if (!lendBookIds.contains(bookId)) {
                throw new BookStoreException(
                        String.format("Book with id: %s cannot be found", cartIn.getCustomerId()),
                        HttpStatus.BAD_REQUEST
                );
            }
        }
    }
}
