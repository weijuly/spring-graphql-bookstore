package org.people.weijuly.bookstore.service;

import org.people.weijuly.bookstore.data.AuthorEntity;
import org.people.weijuly.bookstore.data.AuthorRepository;
import org.people.weijuly.bookstore.model.AuthorModel;
import org.people.weijuly.bookstore.model.AuthorsModel;
import org.people.weijuly.bookstore.util.BookStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public AuthorModel build(String authorId) {
        return authorRepository
                .findById(authorId)
                .map(this::convert)
                .orElseThrow(() -> new BookStoreException(
                        String.format("Cannot find Author[id:%s]", authorId),
                        NOT_FOUND
                ));
    }

    public AuthorsModel findByNameContains(String name) {
        AuthorsModel authorsModel = new AuthorsModel();
        authorsModel.setAuthors(authorRepository
                .findByFirstNameContainingOrLastNameContaining(name, name)
                .stream()
                .map(this::convert)
                .collect(Collectors.toList()));
        return authorsModel;
    }

    private AuthorModel convert(AuthorEntity entity) {
        AuthorModel model = new AuthorModel();
        model.setId(entity.getId());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        return model;
    }

}
