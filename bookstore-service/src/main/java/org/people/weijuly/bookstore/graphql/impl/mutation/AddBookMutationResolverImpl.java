package org.people.weijuly.bookstore.graphql.impl.mutation;

import org.people.weijuly.bookstore.converter.AuthorConverter;
import org.people.weijuly.bookstore.converter.BookConverter;
import org.people.weijuly.bookstore.converter.BookTagConverter;
import org.people.weijuly.bookstore.data.AuthorEntity;
import org.people.weijuly.bookstore.data.AuthorRepository;
import org.people.weijuly.bookstore.data.BookEntity;
import org.people.weijuly.bookstore.data.BookRepository;
import org.people.weijuly.bookstore.data.BookTagEntity;
import org.people.weijuly.bookstore.data.BookTagRepository;
import org.people.weijuly.bookstore.graphql.BookStoreMutationDataFetchers;
import org.people.weijuly.bookstore.model.AddBookMutationResolver;
import org.people.weijuly.bookstore.model.AuthorInModel;
import org.people.weijuly.bookstore.model.AuthorModel;
import org.people.weijuly.bookstore.model.BookInModel;
import org.people.weijuly.bookstore.model.BookModel;
import org.people.weijuly.bookstore.model.BookResultModel;
import org.people.weijuly.bookstore.model.TagModel;
import org.people.weijuly.bookstore.service.AuthorService;
import org.people.weijuly.bookstore.service.BookService;
import org.people.weijuly.bookstore.service.TagsService;
import org.people.weijuly.bookstore.util.BookStoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.people.weijuly.bookstore.util.ErrorUtil.bookStoreError;
import static org.people.weijuly.bookstore.util.LoggerUtil.dump;
import static org.people.weijuly.bookstore.util.LoggerUtil.format;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Component
public class AddBookMutationResolverImpl implements AddBookMutationResolver {

    private static final Logger logger = LoggerFactory.getLogger(BookStoreMutationDataFetchers.class);

    @Autowired
    private BookService bookService;

    @Override
    public BookResultModel addBook(BookInModel bookIn) throws Exception {
        try {
            return bookService.save(bookIn);
        } catch (BookStoreException e) {
            return e.error();
        } catch (Exception e) {
            return new BookStoreException(e, INTERNAL_SERVER_ERROR).error();
        }
    }
}
