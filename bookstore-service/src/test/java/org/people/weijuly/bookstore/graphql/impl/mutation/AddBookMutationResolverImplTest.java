package org.people.weijuly.bookstore.graphql.impl.mutation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.people.weijuly.bookstore.model.BookInModel;
import org.people.weijuly.bookstore.model.BookModel;
import org.people.weijuly.bookstore.model.BookResultModel;
import org.people.weijuly.bookstore.model.BookStoreErrorModel;
import org.people.weijuly.bookstore.service.BookService;
import org.people.weijuly.bookstore.util.BookStoreException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddBookMutationResolverImplTest {

    @Mock
    BookService bookService;

    @Mock
    BookInModel bookIn;

    @InjectMocks
    AddBookMutationResolverImpl resolver;

    @Test
    @DisplayName("addBook should return a book object from service layer on success")
    public void addBookShouldReturnBookFromService() throws Exception{
        when(bookService.save(bookIn)).thenReturn(new BookModel());
        BookResultModel result = resolver.addBook(bookIn);
        assertNotNull(result);
        assertTrue(result instanceof BookModel);
    }

    @Test
    @DisplayName("addBook should return an error object on BookStoreException")
    public void addBookShouldErrorOnBookStoreException() throws Exception{
        when(bookService.save(bookIn)).thenThrow(new BookStoreException());
        BookResultModel result = resolver.addBook(bookIn);
        assertNotNull(result);
        assertTrue(result instanceof BookStoreErrorModel);
    }

    @Test
    @DisplayName("addBook should return an error object on Exception")
    public void addBookShouldErrorOnException() throws Exception{
        when(bookService.save(bookIn)).thenThrow(new NullPointerException());
        BookResultModel result = resolver.addBook(bookIn);
        assertNotNull(result);
        assertTrue(result instanceof BookStoreErrorModel);
    }
}
