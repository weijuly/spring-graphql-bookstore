package org.people.weijuly.bookstore.graphql;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.schema.DataFetcher;
import org.people.weijuly.bookstore.model.AddAuthorMutationResolver;
import org.people.weijuly.bookstore.model.AddAuthorResultModel;
import org.people.weijuly.bookstore.model.AddBookMutationResolver;
import org.people.weijuly.bookstore.model.AddBookResultModel;
import org.people.weijuly.bookstore.model.AuthorInModel;
import org.people.weijuly.bookstore.model.AuthorModel;
import org.people.weijuly.bookstore.model.AuthorsQueryResolver;
import org.people.weijuly.bookstore.model.BookInModel;
import org.people.weijuly.bookstore.model.BookModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.people.weijuly.bookstore.util.BookStoreConstants.bookArg;

@Component
public class BookStoreDataFetchers {

    private static final Logger logger = LoggerFactory.getLogger(BookStoreDataFetchers.class);
    @Autowired
    private AuthorsQueryResolver authorsQueryResolver;
    @Autowired
    private AddAuthorMutationResolver addAuthorMutationResolver;
    @Autowired
    private AddBookMutationResolver addBookMutationResolver;
    @Autowired
    private ObjectMapper mapper;

    public DataFetcher<BookModel> searchBookById() {
        return env -> new BookModel();
    }

    public DataFetcher<List<AuthorModel>> authors() {
        return env -> authorsQueryResolver.authors();
    }

    public DataFetcher<AddAuthorResultModel> addAuthor() {
        return env -> {
            try {
                AuthorInModel authorIn = mapper.convertValue(env.getArgument("author"), AuthorInModel.class);
                return addAuthorMutationResolver.addAuthor(authorIn);
            } catch (IllegalArgumentException e) {
                return null;
            }
        };
    }

    public DataFetcher<AddBookResultModel> addBook() {
        return env -> {
            BookInModel bookIn = mapper.convertValue(env.getArgument(bookArg), BookInModel.class);
            return addBookMutationResolver.addBook(bookIn);
        };
    }


}
