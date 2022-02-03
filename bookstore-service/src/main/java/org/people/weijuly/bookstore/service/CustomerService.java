package org.people.weijuly.bookstore.service;

import org.people.weijuly.bookstore.data.CustomerEntity;
import org.people.weijuly.bookstore.data.CustomerRepository;
import org.people.weijuly.bookstore.model.CustomerInModel;
import org.people.weijuly.bookstore.model.CustomerModel;
import org.people.weijuly.bookstore.model.CustomersModel;
import org.people.weijuly.bookstore.model.CustomersResultModel;
import org.people.weijuly.bookstore.util.BookStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private LendingService lendingService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private BookService bookService;

    public CustomerModel build(String customerId) {
        return customerRepository
                .findById(customerId)
                .map(this::convert)
                .orElseThrow(() -> new BookStoreException(
                        String.format("Customer[id:%s] not found", customerId),
                        NOT_FOUND
                ));
    }

    public CustomerModel save(CustomerInModel customerIn) {
        CustomerEntity customerEntity = customerRepository
                .findByFirstNameAndLastName(customerIn.getFirstName(), customerIn.getLastName())
                .orElseGet(() -> customerRepository.save(convert(customerIn)));
        return convert(customerEntity);
    }

    public boolean didPurchaseOrLend(String customerId, String bookId) {
        return purchaseService.didPurchase(customerId, bookId) || lendingService.didLend(customerId, bookId);
    }

    public boolean exists(String customerId) {
        return customerRepository.existsById(customerId);
    }

    private CustomerModel convert(CustomerEntity customerEntity) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(customerEntity.getId());
        customerModel.setFirstName(customerEntity.getFirstName());
        customerModel.setLastName(customerEntity.getLastName());
        customerModel.setLendings(lendingService.build(customerEntity.getId()));
        customerModel.setPurchases(purchaseService.build(customerEntity.getId()));
        customerModel.setLikes(likeService
                .likedBooks(customerEntity.getId())
                .stream()
                .map(bookService::build)
                .collect(Collectors.toList()));
        return customerModel;
    }

    private CustomerEntity convert(CustomerInModel customerIn) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setFirstName(customerIn.getFirstName());
        customerEntity.setLastName(customerIn.getLastName());
        return customerEntity;
    }

    public CustomersModel searchCustomersByBookLend(String bookId) {
        CustomersModel customersModel = new CustomersModel();
        customersModel.setCustomers(lendingService
                .searchCustomersByBookLend(bookId)
                .stream()
                .map(this::build)
                .collect(Collectors.toList()));
        return customersModel;
    }

    public CustomersResultModel searchCustomersByBookLiked(String bookId) {
        CustomersModel customersModel = new CustomersModel();
        customersModel.setCustomers(likeService
                .searchCustomersByBookLiked(bookId)
                .stream()
                .map(this::build)
                .collect(Collectors.toList()));
        return customersModel;
    }

    public CustomersResultModel searchCustomersByBookPurchased(String bookId) {
        CustomersModel customersModel = new CustomersModel();
        customersModel.setCustomers(purchaseService
                .searchCustomersByBookPurchased(bookId)
                .stream()
                .map(this::build)
                .collect(Collectors.toList()));
        return customersModel;
    }

    public CustomersResultModel searchCustomersByName(String name) {
        CustomersModel customersModel = new CustomersModel();
        customersModel.setCustomers(customerRepository
                .findByFirstNameContainingOrLastNameContaining(name, name)
                .stream()
                .map(this::convert)
                .collect(Collectors.toList()));
        return customersModel;
    }
}
