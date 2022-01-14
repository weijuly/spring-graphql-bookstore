package org.people.weijuly.bookstore.graphql.impl.mutation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.people.weijuly.bookstore.data.AuthorEntity;
import org.people.weijuly.bookstore.data.AuthorRepository;
import org.people.weijuly.bookstore.data.BookEntity;
import org.people.weijuly.bookstore.data.BookRepository;
import org.people.weijuly.bookstore.model.AddBookResultModel;
import org.people.weijuly.bookstore.model.AuthorInModel;
import org.people.weijuly.bookstore.model.BookInModel;
import org.people.weijuly.bookstore.model.BookModel;
import org.people.weijuly.bookstore.model.BookStoreErrorModel;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class AddBookMutationResolverImplTest {

    @Mock
    BookRepository bookRepository;

    @Mock
    AuthorRepository authorRepository;

    @InjectMocks
    AddBookMutationResolverImpl impl;

    @Test
    public void shouldCreateAuthorAndBookWhenNotExists() throws Exception {
        lenient()
                .when(authorRepository.findByFirstNameAndLastName(anyString(), anyString()))
                .thenReturn(Optional.empty());
        lenient()
                .when(authorRepository.save(any())).thenReturn(authorEntity());
        lenient()
                .when(bookRepository.findByName(anyString())).thenReturn(Optional.of(bookEntity()));
        lenient()
                .when(bookRepository.save(any())).thenReturn(bookEntity());
        AddBookResultModel result = impl.addBook(bookIn());
        assertTrue(result instanceof BookModel);

    }


    @Test
    public void shouldReturnBookStoreErrorOnException() throws Exception {
        doThrow(new RuntimeException())
                .when(authorRepository)
                .findByFirstNameAndLastName(authorIn().getFirstName(), authorIn().getLastName());
        AddBookResultModel result = impl.addBook(bookIn());
        assertTrue(result instanceof BookStoreErrorModel);
    }

    private BookInModel bookIn() {
        BookInModel bookIn = new BookInModel();
        bookIn.setAuthor(authorIn());
        bookIn.setIsbn("ISBN");
        bookIn.setCopies(1234);
        bookIn.setPrice(2345);
        bookIn.setPages(3456);
        bookIn.setYear(4567);
        bookIn.setName("NAME");
        bookIn.setTags(Collections.emptyList());
        return bookIn;
    }

    private AuthorInModel authorIn() {
        AuthorInModel authorIn = new AuthorInModel();
        authorIn.setFirstName("Jules");
        authorIn.setFirstName("Verne");
        return authorIn;
    }

    private AuthorEntity authorEntity() {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setFirstName("Jules");
        authorEntity.setFirstName("Verne");
        return authorEntity;
    }

    private BookEntity bookEntity() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setIsbn("ISBN");
        bookEntity.setCopies(1234);
        bookEntity.setPrice(2345);
        bookEntity.setPages(3456);
        bookEntity.setYear(4567);
        bookEntity.setName("NAME");
        bookEntity.setUpdatedOn(new Date());
        return bookEntity;
    }

}
