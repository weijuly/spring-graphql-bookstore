package org.people.weijuly.bookstore.graphql.impl.mutation;

import org.people.weijuly.bookstore.converter.AuthorConverter;
import org.people.weijuly.bookstore.data.AuthorRepository;
import org.people.weijuly.bookstore.graphql.BookStoreDataFetchers;
import org.people.weijuly.bookstore.model.AddAuthorErrorModel;
import org.people.weijuly.bookstore.model.AddAuthorMutationResolver;
import org.people.weijuly.bookstore.model.AddAuthorResultModel;
import org.people.weijuly.bookstore.model.AuthorInModel;
import org.people.weijuly.bookstore.util.BookStoreException;
import org.people.weijuly.bookstore.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.CannotCreateTransactionException;

import java.util.Optional;

@Component
public class AddAuthorMutationResolverImpl implements AddAuthorMutationResolver {

    private static final Logger logger = LoggerFactory.getLogger(BookStoreDataFetchers.class);

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public AddAuthorResultModel addAuthor(AuthorInModel authorIn) throws Exception {
        logger.info("AddAuthorMutationResolverImpl.addAuthor: req: {}", authorIn);
        try {
            return Optional
                    .of(authorIn)
                    .map(AuthorConverter::convert)
                    .map(authorRepository::save)
                    .map(AuthorConverter::convert)
                    .orElseThrow(BookStoreException::new);
        } catch (DataIntegrityViolationException e) {
            logger.error(ExceptionUtil.format(e));
            return authorRepository
                    .findByFirstNameAndLastName(authorIn.getFirstName(), authorIn.getLastName())
                    .map(AuthorConverter::convert)
                    .orElseThrow(BookStoreException::new);
        } catch (CannotCreateTransactionException | BookStoreException e) {
            return error(ExceptionUtil.format(e));
        }
    }

    private AddAuthorErrorModel error(String message) {
        logger.error(message);
        AddAuthorErrorModel error = new AddAuthorErrorModel();
        error.setMessage(String.format("Internal Server Error: %s", message));
        error.setStatus(500);
        return error;
    }
}
