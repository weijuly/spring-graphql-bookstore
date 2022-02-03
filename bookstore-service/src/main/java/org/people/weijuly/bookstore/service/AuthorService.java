package org.people.weijuly.bookstore.service;

import org.people.weijuly.bookstore.data.AuthorEntity;
import org.people.weijuly.bookstore.data.AuthorRepository;
import org.people.weijuly.bookstore.model.AuthorInModel;
import org.people.weijuly.bookstore.model.AuthorModel;
import org.people.weijuly.bookstore.model.AuthorsModel;
import org.people.weijuly.bookstore.util.BookStoreException;
import org.springframework.beans.factory.annotation.Autowired;
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

    public AuthorModel save(AuthorInModel authorIn) {
        AuthorEntity authorEntity = authorRepository
                .findByFirstNameAndLastName(authorIn.getFirstName(), authorIn.getLastName())
                .orElseGet(() -> authorRepository.save(convert(authorIn)));
        return convert(authorEntity);
    }

    private AuthorModel convert(AuthorEntity authorEntity) {
        AuthorModel authorModel = new AuthorModel();
        authorModel.setId(authorEntity.getId());
        authorModel.setFirstName(authorEntity.getFirstName());
        authorModel.setLastName(authorEntity.getLastName());
        return authorModel;
    }

    private AuthorEntity convert(AuthorInModel authorIn) {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setFirstName(authorIn.getFirstName());
        authorEntity.setLastName(authorIn.getLastName());
        return authorEntity;
    }

}
