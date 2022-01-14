package org.people.weijuly.bookstore.graphql;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.schema.DataFetcher;
import org.people.weijuly.bookstore.model.AddBookMutationResolver;
import org.people.weijuly.bookstore.model.AddBookResultModel;
import org.people.weijuly.bookstore.model.AuthorModel;
import org.people.weijuly.bookstore.model.AuthorsQueryResolver;
import org.people.weijuly.bookstore.model.BookInModel;
import org.people.weijuly.bookstore.model.BookModel;
import org.people.weijuly.bookstore.model.SearchBookByAuthorQueryResolver;
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
    private AddBookMutationResolver addBookMutationResolver;

    @Autowired
    private SearchBookByAuthorQueryResolver searchBookByAuthorQueryResolver;

    @Autowired
    private ObjectMapper mapper;

    public DataFetcher<BookModel> searchBookById() {
        return env -> new BookModel();
    }

    public DataFetcher<List<AuthorModel>> authors() {
        return env -> authorsQueryResolver.authors();
    }

    public DataFetcher<AddBookResultModel> addBook() {
        return env -> {
            BookInModel bookIn = mapper.convertValue(env.getArgument(bookArg), BookInModel.class);
            return addBookMutationResolver.addBook(bookIn);
        };
    }

    public DataFetcher<List<BookModel>> searchBookByAuthor() {
        return env -> {
            String name = mapper.convertValue(env.getArgument("name"), String.class);
            return searchBookByAuthorQueryResolver.searchBookByAuthor(name);
        };
    }


}
