package org.people.weijuly.bookstore.graphql.impl.query;

import org.people.weijuly.bookstore.model.CustomerResultModel;
import org.people.weijuly.bookstore.model.SearchCustomerByIdQueryResolver;
import org.people.weijuly.bookstore.service.CustomerService;
import org.people.weijuly.bookstore.util.BookStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Component
public class SearchCustomerByIdQueryResolverImpl implements SearchCustomerByIdQueryResolver {

    @Autowired
    CustomerService customerService;

    @Override
    public CustomerResultModel searchCustomerById(String customerId) throws Exception {
        try {
            return customerService.build(customerId);
        } catch (BookStoreException e) {
            return e.error();
        } catch (Exception e) {
            return new BookStoreException(e, INTERNAL_SERVER_ERROR).error();
        }
    }
}
