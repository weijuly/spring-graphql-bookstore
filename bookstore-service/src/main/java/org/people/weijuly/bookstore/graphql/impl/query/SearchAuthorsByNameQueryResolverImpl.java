package org.people.weijuly.bookstore.graphql.impl.query;

import org.people.weijuly.bookstore.model.AuthorsResultModel;
import org.people.weijuly.bookstore.model.SearchAuthorsByNameQueryResolver;
import org.people.weijuly.bookstore.service.AuthorService;
import org.people.weijuly.bookstore.util.BookStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Component
public class SearchAuthorsByNameQueryResolverImpl implements SearchAuthorsByNameQueryResolver {

    @Autowired
    private AuthorService authorService;

    @Override
    public AuthorsResultModel searchAuthorsByName(String name) throws Exception {
        try {
            return authorService.findByNameContains(name);
        } catch (BookStoreException e) {
            return e.error();
        } catch (Exception e) {
            return new BookStoreException(e, INTERNAL_SERVER_ERROR).error();
        }
    }
}
