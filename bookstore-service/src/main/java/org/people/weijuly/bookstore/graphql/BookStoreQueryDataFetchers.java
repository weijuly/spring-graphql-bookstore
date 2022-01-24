package org.people.weijuly.bookstore.graphql;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.schema.DataFetcher;
import org.people.weijuly.bookstore.model.AuthorResultModel;
import org.people.weijuly.bookstore.model.AuthorsResultModel;
import org.people.weijuly.bookstore.model.BookResultModel;
import org.people.weijuly.bookstore.model.BooksResultModel;
import org.people.weijuly.bookstore.model.CustomerResultModel;
import org.people.weijuly.bookstore.model.CustomersResultModel;
import org.people.weijuly.bookstore.model.SearchAuthorByIdQueryResolver;
import org.people.weijuly.bookstore.model.SearchAuthorsByNameQueryResolver;
import org.people.weijuly.bookstore.model.SearchBookByIdQueryResolver;
import org.people.weijuly.bookstore.model.SearchBooksByAuthorIdQueryResolver;
import org.people.weijuly.bookstore.model.SearchBooksByAuthorNameQueryResolver;
import org.people.weijuly.bookstore.model.SearchBooksByNameQueryResolver;
import org.people.weijuly.bookstore.model.SearchBooksByTagQueryResolver;
import org.people.weijuly.bookstore.model.SearchBooksByYearQueryResolver;
import org.people.weijuly.bookstore.model.SearchCustomerByIdQueryResolver;
import org.people.weijuly.bookstore.model.SearchCustomersByBookLendQueryResolver;
import org.people.weijuly.bookstore.model.SearchCustomersByBookLikedQueryResolver;
import org.people.weijuly.bookstore.model.SearchCustomersByBookPurchasedQueryResolver;
import org.people.weijuly.bookstore.model.SearchCustomersByNameQueryResolver;
import org.people.weijuly.bookstore.model.TagModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.people.weijuly.bookstore.util.BookStoreConstants.authorIdArg;
import static org.people.weijuly.bookstore.util.BookStoreConstants.bookIdArg;
import static org.people.weijuly.bookstore.util.BookStoreConstants.customerIdArg;
import static org.people.weijuly.bookstore.util.BookStoreConstants.nameArg;
import static org.people.weijuly.bookstore.util.BookStoreConstants.tagArg;
import static org.people.weijuly.bookstore.util.BookStoreConstants.yearArg;

@Component
public class BookStoreQueryDataFetchers {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    SearchAuthorByIdQueryResolver searchAuthorByIdQueryResolver;

    @Autowired
    SearchAuthorsByNameQueryResolver searchAuthorsByNameQueryResolver;

    @Autowired
    SearchBookByIdQueryResolver searchBookByIdQueryResolver;

    @Autowired
    SearchBooksByAuthorIdQueryResolver searchBooksByAuthorIdQueryResolver;

    @Autowired
    SearchBooksByAuthorNameQueryResolver searchBooksByAuthorNameQueryResolver;

    @Autowired
    SearchBooksByNameQueryResolver searchBooksByNameQueryResolver;

    @Autowired
    SearchBooksByTagQueryResolver searchBooksByTagQueryResolver;

    @Autowired
    SearchBooksByYearQueryResolver searchBooksByYearQueryResolver;

    @Autowired
    SearchCustomerByIdQueryResolver searchCustomerByIdQueryResolver;

    @Autowired
    SearchCustomersByBookLendQueryResolver searchCustomersByBookLendQueryResolver;

    @Autowired
    SearchCustomersByBookLikedQueryResolver searchCustomersByBookLikedQueryResolver;

    @Autowired
    SearchCustomersByBookPurchasedQueryResolver searchCustomersByBookPurchasedQueryResolver;

    @Autowired
    SearchCustomersByNameQueryResolver searchCustomersByNameQueryResolver;

    public DataFetcher<AuthorResultModel> searchAuthorById() {
        return env -> {
            String bookId = mapper.convertValue(env.getArgument(authorIdArg), String.class);
            return searchAuthorByIdQueryResolver.searchAuthorById(bookId);
        };
    }


    public DataFetcher<AuthorsResultModel> searchAuthorsByName() {
        return env -> {
            String name = mapper.convertValue(env.getArgument(nameArg), String.class);
            return searchAuthorsByNameQueryResolver.searchAuthorsByName(name);
        };
    }


    public DataFetcher<BookResultModel> searchBookById() {
        return env -> {
            String bookId = mapper.convertValue(env.getArgument(bookIdArg), String.class);
            return searchBookByIdQueryResolver.searchBookById(bookId);
        };
    }


    public DataFetcher<BooksResultModel> searchBooksByAuthorId() {
        return env -> {
            String authorId = mapper.convertValue(env.getArgument(authorIdArg), String.class);
            return searchBooksByAuthorIdQueryResolver.searchBooksByAuthorId(authorId);
        };
    }


    public DataFetcher<BooksResultModel> searchBooksByAuthorName() {
        return env -> {
            String name = mapper.convertValue(env.getArgument(nameArg), String.class);
            return searchBooksByAuthorNameQueryResolver.searchBooksByAuthorName(name);
        };
    }


    public DataFetcher<BooksResultModel> searchBooksByName() {
        return env -> {
            String name = mapper.convertValue(env.getArgument(nameArg), String.class);
            return searchBooksByNameQueryResolver.searchBooksByName(name);
        };
    }


    public DataFetcher<BooksResultModel> searchBooksByTag() {
        return env -> {
            TagModel tag = mapper.convertValue(env.getArgument(tagArg), TagModel.class);
            return searchBooksByTagQueryResolver.searchBooksByTag(tag);
        };
    }


    public DataFetcher<BooksResultModel> searchBooksByYear() {
        return env -> {
            Integer year = mapper.convertValue(env.getArgument(yearArg), Integer.class);
            return searchBooksByYearQueryResolver.searchBooksByYear(year);
        };
    }


    public DataFetcher<CustomerResultModel> searchCustomerById() {
        return env -> {
            String customerId = mapper.convertValue(env.getArgument(customerIdArg), String.class);
            return searchCustomerByIdQueryResolver.searchCustomerById(customerId);
        };
    }


    public DataFetcher<CustomersResultModel> searchCustomersByBookLend() {
        return env -> {
            String bookId = mapper.convertValue(env.getArgument(bookIdArg), String.class);
            return searchCustomersByBookLendQueryResolver.searchCustomersByBookLend(bookId);
        };
    }


    public DataFetcher<CustomersResultModel> searchCustomersByBookLiked() {
        return env -> {
            String bookId = mapper.convertValue(env.getArgument(bookIdArg), String.class);
            return searchCustomersByBookLikedQueryResolver.searchCustomersByBookLiked(bookId);
        };
    }


    public DataFetcher<CustomersResultModel> searchCustomersByBookPurchased() {
        return env -> {
            String bookId = mapper.convertValue(env.getArgument(bookIdArg), String.class);
            return searchCustomersByBookPurchasedQueryResolver.searchCustomersByBookPurchased(bookId);
        };
    }


    public DataFetcher<CustomersResultModel> searchCustomersByName() {
        return env -> {
            String name = mapper.convertValue(env.getArgument(nameArg), String.class);
            return searchCustomersByNameQueryResolver.searchCustomersByName(name);
        };
    }

}
