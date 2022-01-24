package org.people.weijuly.bookstore.graphql.impl.mutation;

import org.people.weijuly.bookstore.converter.CustomerConverter;
import org.people.weijuly.bookstore.data.CustomerEntity;
import org.people.weijuly.bookstore.data.CustomerRepository;
import org.people.weijuly.bookstore.model.AddCustomerMutationResolver;
import org.people.weijuly.bookstore.model.BookStoreErrorModel;
import org.people.weijuly.bookstore.model.CustomerInModel;
import org.people.weijuly.bookstore.model.CustomerModel;
import org.people.weijuly.bookstore.model.CustomerResultModel;
import org.people.weijuly.bookstore.service.CustomerService;
import org.people.weijuly.bookstore.util.BookStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Component
public class AddCustomerMutationResolverImpl implements AddCustomerMutationResolver {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Override
    public CustomerResultModel addCustomer(CustomerInModel customerIn) throws Exception {
        try {
            CustomerEntity customerEntity = customerRepository
                    .findByFirstNameAndLastName(customerIn.getFirstName(), customerIn.getLastName())
                    .orElseGet(() -> save(customerIn));
            CustomerModel c = customerService.build(customerEntity.getId());
            return customerService.build(customerEntity.getId());
        } catch (BookStoreException e) {
            return e.error();
        } catch (Exception e) {
            return new BookStoreException(e, INTERNAL_SERVER_ERROR).error();
        }
    }

    private CustomerEntity save(CustomerInModel customerIn) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setFirstName(customerIn.getFirstName());
        customerEntity.setLastName(customerIn.getLastName());
        return customerRepository.save(customerEntity);
    }
}
