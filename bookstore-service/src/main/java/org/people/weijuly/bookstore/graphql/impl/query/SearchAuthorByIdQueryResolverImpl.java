package org.people.weijuly.bookstore.graphql.impl.query;

import org.people.weijuly.bookstore.model.AuthorResultModel;
import org.people.weijuly.bookstore.model.SearchAuthorByIdQueryResolver;
import org.people.weijuly.bookstore.service.AuthorService;
import org.people.weijuly.bookstore.util.BookStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Component
public class SearchAuthorByIdQueryResolverImpl implements SearchAuthorByIdQueryResolver {

    @Autowired
    private AuthorService authorService;

    @Override
    public AuthorResultModel searchAuthorById(String authorId) throws Exception {
        try {
            return authorService.build(authorId);
        } catch (BookStoreException e) {
            return e.error();
        } catch (Exception e) {
            return new BookStoreException(e, INTERNAL_SERVER_ERROR).error();
        }
    }
}
