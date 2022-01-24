package org.people.weijuly.bookstore.graphql.impl.query;

import org.people.weijuly.bookstore.model.CustomersResultModel;
import org.people.weijuly.bookstore.model.SearchCustomersByNameQueryResolver;
import org.people.weijuly.bookstore.service.CustomerService;
import org.people.weijuly.bookstore.util.BookStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Component
public class SearchCustomersByNameQueryResolverImpl implements SearchCustomersByNameQueryResolver {

    @Autowired
    CustomerService customerService;

    @Override
    public CustomersResultModel searchCustomersByName(String name) throws Exception {
        try {
            return customerService.searchCustomersByName(name);
        } catch (BookStoreException e) {
            return e.error();
        } catch (Exception e) {
            return new BookStoreException(e, INTERNAL_SERVER_ERROR).error();
        }
    }

}
