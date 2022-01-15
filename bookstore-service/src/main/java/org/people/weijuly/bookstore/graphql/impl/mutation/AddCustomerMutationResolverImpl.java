package org.people.weijuly.bookstore.graphql.impl.mutation;

import org.people.weijuly.bookstore.converter.CustomerConverter;
import org.people.weijuly.bookstore.data.CustomerEntity;
import org.people.weijuly.bookstore.data.CustomerRepository;
import org.people.weijuly.bookstore.model.AddCustomerMutationResolver;
import org.people.weijuly.bookstore.model.AddCustomerResultModel;
import org.people.weijuly.bookstore.model.BookStoreErrorModel;
import org.people.weijuly.bookstore.model.CustomerInModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddCustomerMutationResolverImpl implements AddCustomerMutationResolver {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public AddCustomerResultModel addCustomer(CustomerInModel customerIn) throws Exception {
        try {
            CustomerEntity customerEntity = customerRepository
                    .findByFirstNameAndLastName(customerIn.getFirstName(), customerIn.getLastName())
                    .orElseGet(() -> save(customerIn));
            return CustomerConverter.convert(customerEntity);
        } catch (Exception e) {
            return new BookStoreErrorModel();
        }
    }

    private CustomerEntity save(CustomerInModel customerIn) {
        return customerRepository.save(CustomerConverter.convert(customerIn));
    }
}
