package org.people.weijuly.bookstore.graphql.impl.mutation;

import org.people.weijuly.bookstore.converter.AuthorConverter;
import org.people.weijuly.bookstore.converter.BookConverter;
import org.people.weijuly.bookstore.converter.BookTagConverter;
import org.people.weijuly.bookstore.data.AuthorEntity;
import org.people.weijuly.bookstore.data.AuthorRepository;
import org.people.weijuly.bookstore.data.BookEntity;
import org.people.weijuly.bookstore.data.BookRepository;
import org.people.weijuly.bookstore.data.BookTagEntity;
import org.people.weijuly.bookstore.data.BookTagRepository;
import org.people.weijuly.bookstore.graphql.BookStoreMutationDataFetchers;
import org.people.weijuly.bookstore.model.AddBookMutationResolver;
import org.people.weijuly.bookstore.model.AuthorInModel;
import org.people.weijuly.bookstore.model.BookInModel;
import org.people.weijuly.bookstore.model.BookResultModel;
import org.people.weijuly.bookstore.model.TagModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.people.weijuly.bookstore.util.ErrorUtil.bookStoreError;
import static org.people.weijuly.bookstore.util.LoggerUtil.dump;
import static org.people.weijuly.bookstore.util.LoggerUtil.format;

@Component
public class AddBookMutationResolverImpl implements AddBookMutationResolver {

    private static final Logger logger = LoggerFactory.getLogger(BookStoreMutationDataFetchers.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookTagRepository bookTagRepository;

    @Override
    public BookResultModel addBook(BookInModel bookIn) throws Exception {
        try {
            AuthorEntity authorEntity = fetchOrSave(bookIn.getAuthor());
            BookEntity bookEntity = fetchOrSave(bookIn, authorEntity);
            List<BookTagEntity> bookTagsEntities = fetchOrSave(bookIn.getTags(), bookEntity.getId());
            return BookConverter.convert(bookEntity, authorEntity, bookTagsEntities);
        } catch (Exception e) {
            logger.error(format(e));
            return bookStoreError();
        }
    }

    private AuthorEntity fetchOrSave(AuthorInModel authorIn) {
        return authorRepository
                .findByFirstNameAndLastName(authorIn.getFirstName(), authorIn.getLastName())
                .orElseGet(() -> save(authorIn));
    }

    private AuthorEntity save(AuthorInModel authorIn) {
        logger.info("AddBookMutationResolverImpl: save: {}", dump(authorIn));
        return authorRepository.save(AuthorConverter.convert(authorIn));
    }

    private BookEntity fetchOrSave(BookInModel bookIn, AuthorEntity authorEntity) {
        return bookRepository
                .findByName(bookIn.getName())
                .map(bookEntity -> update(bookEntity, bookIn))
                .orElseGet(() -> save(bookIn, authorEntity.getId()));
    }

    private BookEntity update(BookEntity bookEntity, BookInModel bookIn) {
        bookEntity.setCopies(bookEntity.getCopies() + bookIn.getCopies());
        bookEntity.setPrice(bookEntity.getPrice());
        logger.info("AddBookMutationResolverImpl: save: {}", dump(bookEntity));
        return bookRepository.save(bookEntity);
    }

    private BookEntity save(BookInModel bookIn, String authorId) {
        logger.info("AddBookMutationResolverImpl: save: {}", dump(bookIn));
        return bookRepository.save(BookConverter.convert(bookIn, authorId));

    }

    private List<BookTagEntity> fetchOrSave(List<TagModel> tags, String bookId) {
        List<BookTagEntity> bookTagEntities = bookTagRepository.findByBookId(bookId);
        if (!bookTagEntities.isEmpty()) {
            return bookTagEntities;
        }
        return tags
                .stream()
                .map(tag -> BookTagConverter.convert(tag, bookId))
                .map(bookTagRepository::save)
                .collect(Collectors.toList());
    }
}
