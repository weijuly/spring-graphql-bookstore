package org.people.weijuly.bookstore.graphql.impl.query;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.people.weijuly.bookstore.model.AuthorModel;
import org.people.weijuly.bookstore.model.AuthorResultModel;
import org.people.weijuly.bookstore.model.BookStoreErrorModel;
import org.people.weijuly.bookstore.service.AuthorService;
import org.people.weijuly.bookstore.util.BookStoreException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchAuthorByIdQueryResolverImplTest {

    @Mock
    AuthorService authorService;

    @InjectMocks
    SearchAuthorByIdQueryResolverImpl resolver;

    private static final String authorId = "id";

    @Test
    @DisplayName("searchAuthorById should return Author on success")
    public void searchAuthorByIdShouldReturnAuthorOnSuccess() throws Exception {
        when(authorService.build(authorId)).thenReturn(new AuthorModel());
        AuthorResultModel result = resolver.searchAuthorById(authorId);
        assertNotNull(result);
        assertTrue(result instanceof AuthorModel);
    }

    @Test
    @DisplayName("searchAuthorById should return BookStoreError on BookStoreException")
    public void searchAuthorByIdShouldReturnErrorOnBookStoreException() throws Exception {
        when(authorService.build(authorId)).thenThrow(new BookStoreException());
        AuthorResultModel result = resolver.searchAuthorById(authorId);
        assertNotNull(result);
        assertTrue(result instanceof BookStoreErrorModel);
    }

    @Test
    @DisplayName("searchAuthorById should return BookStoreError on Exception")
    public void searchAuthorByIdShouldReturnErrorOnException() throws Exception {
        when(authorService.build(authorId)).thenThrow(new NullPointerException());
        AuthorResultModel result = resolver.searchAuthorById(authorId);
        assertNotNull(result);
        assertTrue(result instanceof BookStoreErrorModel);
    }
}
