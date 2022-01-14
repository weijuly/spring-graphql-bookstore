package org.people.weijuly.bookstore.graphql.impl.mutation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.people.weijuly.bookstore.converter.AuthorConverter;
import org.people.weijuly.bookstore.converter.BookConverter;
import org.people.weijuly.bookstore.data.AuthorEntity;
import org.people.weijuly.bookstore.data.AuthorRepository;
import org.people.weijuly.bookstore.data.BookEntity;
import org.people.weijuly.bookstore.data.BookRepository;
import org.people.weijuly.bookstore.graphql.BookStoreDataFetchers;
import org.people.weijuly.bookstore.model.AddBookMutationResolver;
import org.people.weijuly.bookstore.model.AddBookResultModel;
import org.people.weijuly.bookstore.model.AuthorInModel;
import org.people.weijuly.bookstore.model.BookInModel;
import org.people.weijuly.bookstore.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Component;

import static org.people.weijuly.bookstore.util.ErrorUtil.bookStoreError;
import static org.people.weijuly.bookstore.util.LoggerUtil.dump;
import static org.people.weijuly.bookstore.util.LoggerUtil.format;

@Component
public class AddBookMutationResolverImpl implements AddBookMutationResolver {

	private static final Logger logger = LoggerFactory.getLogger(BookStoreDataFetchers.class);

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public AddBookResultModel addBook(BookInModel bookIn) throws Exception {
		try {
			AuthorEntity authorEntity = fetchOrSave(bookIn.getAuthor());
			BookEntity bookEntity = fetchOrSave(bookIn, authorEntity);
			return BookConverter.convert(bookEntity, authorEntity);
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
}
