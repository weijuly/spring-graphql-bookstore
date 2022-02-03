package org.people.weijuly.bookstore.graphql.impl.mutation;

import org.people.weijuly.bookstore.model.AddCustomerMutationResolver;
import org.people.weijuly.bookstore.model.CustomerInModel;
import org.people.weijuly.bookstore.model.CustomerResultModel;
import org.people.weijuly.bookstore.service.CustomerService;
import org.people.weijuly.bookstore.util.BookStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Component
public class AddCustomerMutationResolverImpl implements AddCustomerMutationResolver {

    @Autowired
    private CustomerService customerService;

    @Override
    public CustomerResultModel addCustomer(CustomerInModel customerIn) throws Exception {
        try {
            return customerService.save(customerIn);
        } catch (BookStoreException e) {
            return e.error();
        } catch (Exception e) {
            return new BookStoreException(e, INTERNAL_SERVER_ERROR).error();
        }
    }

}
