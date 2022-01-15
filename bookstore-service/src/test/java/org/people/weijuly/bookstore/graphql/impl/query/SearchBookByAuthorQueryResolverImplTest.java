package org.people.weijuly.bookstore.graphql.impl.query;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.people.weijuly.bookstore.data.AuthorEntity;
import org.people.weijuly.bookstore.data.AuthorRepository;
import org.people.weijuly.bookstore.data.BookEntity;
import org.people.weijuly.bookstore.data.BookRepository;
import org.people.weijuly.bookstore.model.BookModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class SearchBookByAuthorQueryResolverImplTest {

    @Mock
    AuthorRepository authorRepository;

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    SearchBookByAuthorQueryResolverImpl impl;

    @Test
    public void shouldReturnMatchingBooks() throws Exception {
        lenient()
                .when(authorRepository.findByFirstNameContainingOrLastNameContaining(anyString(), anyString()))
                .thenReturn(Collections.singletonList(authorEntity()));
        lenient()
                .when(bookRepository.findByAuthorId(anyString()))
                .thenReturn(Arrays.asList(bookEntity(), bookEntity()));
        List<BookModel> books = impl.searchBookByAuthor("name");
        assertNotNull(books);
        System.out.println(books.size());
        // TODO: fix this: assertEquals(books.size(), 4);
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

    private AuthorEntity authorEntity() {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setFirstName("Jules");
        authorEntity.setFirstName("Verne");
        return authorEntity;
    }
}
