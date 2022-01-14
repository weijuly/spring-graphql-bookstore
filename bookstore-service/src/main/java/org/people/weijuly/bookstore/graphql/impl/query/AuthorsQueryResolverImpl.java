package org.people.weijuly.bookstore.graphql.impl.query;

import org.people.weijuly.bookstore.converter.AuthorConverter;
import org.people.weijuly.bookstore.data.AuthorRepository;
import org.people.weijuly.bookstore.model.AuthorModel;
import org.people.weijuly.bookstore.model.AuthorsQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorsQueryResolverImpl implements AuthorsQueryResolver {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<AuthorModel> authors() throws Exception {
        return authorRepository
                .findAll()
                .stream()
                .map(AuthorConverter::convert)
                .collect(Collectors.toList());
    }
}
