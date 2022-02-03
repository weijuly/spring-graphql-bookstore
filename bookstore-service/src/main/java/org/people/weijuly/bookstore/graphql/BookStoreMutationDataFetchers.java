package org.people.weijuly.bookstore.graphql;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.schema.DataFetcher;
import org.people.weijuly.bookstore.model.AddBookMutationResolver;
import org.people.weijuly.bookstore.model.AddCustomerMutationResolver;
import org.people.weijuly.bookstore.model.BookInModel;
import org.people.weijuly.bookstore.model.BookResultModel;
import org.people.weijuly.bookstore.model.CartInModel;
import org.people.weijuly.bookstore.model.CustomerInModel;
import org.people.weijuly.bookstore.model.CustomerResultModel;
import org.people.weijuly.bookstore.model.LendBooksMutationResolver;
import org.people.weijuly.bookstore.model.LikeBookMutationResolver;
import org.people.weijuly.bookstore.model.LikeInModel;
import org.people.weijuly.bookstore.model.PurchaseBooksMutationResolver;
import org.people.weijuly.bookstore.model.ReturnBooksMutationResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.people.weijuly.bookstore.util.BookStoreConstants.bookArg;
import static org.people.weijuly.bookstore.util.BookStoreConstants.cartArg;
import static org.people.weijuly.bookstore.util.BookStoreConstants.customerArg;
import static org.people.weijuly.bookstore.util.BookStoreConstants.likeArg;

@Component
public class BookStoreMutationDataFetchers {

    private static final Logger logger = LoggerFactory.getLogger(BookStoreMutationDataFetchers.class);

    @Autowired
    private AddBookMutationResolver addBookMutationResolver;

    @Autowired
    private AddCustomerMutationResolver addCustomerMutationResolver;

    @Autowired
    private PurchaseBooksMutationResolver purchaseBooksMutationResolver;

    @Autowired
    private LendBooksMutationResolver lendBooksMutationResolver;

    @Autowired
    private ReturnBooksMutationResolver returnBooksMutationResolver;

    @Autowired
    private LikeBookMutationResolver likeBookMutationResolver;

    @Autowired
    private ObjectMapper mapper;

    public DataFetcher<BookResultModel> addBook() {
        return env -> {
            BookInModel bookIn = mapper.convertValue(env.getArgument(bookArg), BookInModel.class);
            return addBookMutationResolver.addBook(bookIn);
        };
    }

    public DataFetcher<CustomerResultModel> addCustomer() {
        return env -> {
            CustomerInModel customerIn = mapper.convertValue(env.getArgument(customerArg), CustomerInModel.class);
            return addCustomerMutationResolver.addCustomer(customerIn);
        };
    }

    public DataFetcher<CustomerResultModel> purchaseBooks() {
        return env -> {
            CartInModel cartIn = mapper.convertValue(env.getArgument(cartArg), CartInModel.class);
            return purchaseBooksMutationResolver.purchaseBooks(cartIn);
        };
    }

    public DataFetcher<CustomerResultModel> lendBooks() {
        return env -> {
            CartInModel cartIn = mapper.convertValue(env.getArgument(cartArg), CartInModel.class);
            return lendBooksMutationResolver.lendBooks(cartIn);
        };
    }

    public DataFetcher<CustomerResultModel> returnBooks() {
        return env -> {
            CartInModel cartIn = mapper.convertValue(env.getArgument(cartArg), CartInModel.class);
            return returnBooksMutationResolver.returnBooks(cartIn);
        };
    }

    public DataFetcher<CustomerResultModel> likeBook() {
        return env -> {
            LikeInModel likeIn = mapper.convertValue(env.getArgument(likeArg), LikeInModel.class);
            return likeBookMutationResolver.likeBook(likeIn);
        };
    }

}
