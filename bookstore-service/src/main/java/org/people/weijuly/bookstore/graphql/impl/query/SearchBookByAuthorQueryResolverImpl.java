package org.people.weijuly.bookstore.graphql.impl.query;

import org.people.weijuly.bookstore.converter.BookConverter;
import org.people.weijuly.bookstore.data.AuthorEntity;
import org.people.weijuly.bookstore.data.AuthorRepository;
import org.people.weijuly.bookstore.data.BookRepository;
import org.people.weijuly.bookstore.data.BookTagRepository;
import org.people.weijuly.bookstore.model.BookModel;
import org.people.weijuly.bookstore.model.SearchBookByAuthorQueryResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class SearchBookByAuthorQueryResolverImpl implements SearchBookByAuthorQueryResolver {

    private static final Logger logger = LoggerFactory.getLogger(SearchBookByAuthorQueryResolverImpl.class);

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookTagRepository bookTagRepository;

    @Override
    public List<BookModel> searchBookByAuthor(String name) throws Exception {
        return authorRepository
                .findByFirstNameContainingOrLastNameContaining(name, name)
                .stream()
                .flatMap(this::booksByAuthorId)
                .collect(Collectors.toList());
    }

    private Stream<BookModel> booksByAuthorId(AuthorEntity authorEntity) {
        return bookRepository
                .findByAuthorId(authorEntity.getId())
                .stream()
                .map(bookEntity -> BookConverter.convert(
                        bookEntity, authorEntity, bookTagRepository.findByBookId(bookEntity.getId())));
    }

}
