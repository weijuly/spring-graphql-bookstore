package org.people.weijuly.bookstore.graphql;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.schema.DataFetcher;
import org.people.weijuly.bookstore.model.AddAuthorMutationResolver;
import org.people.weijuly.bookstore.model.AddAuthorResultModel;
import org.people.weijuly.bookstore.model.AuthorInModel;
import org.people.weijuly.bookstore.model.AuthorModel;
import org.people.weijuly.bookstore.model.AuthorsQueryResolver;
import org.people.weijuly.bookstore.model.BookModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphQLDataFetchers {

	@Autowired
	private AuthorsQueryResolver authorsQueryResolver;

	@Autowired
	private AddAuthorMutationResolver addAuthorMutationResolver;

	@Autowired
	private ObjectMapper mapper;

	private static final Logger logger = LoggerFactory.getLogger(GraphQLDataFetchers.class);

	public DataFetcher<BookModel> searchBookById() {
		return env -> new BookModel();
	}

	public DataFetcher<List<AuthorModel>> authors() {
		return env -> authorsQueryResolver.authors();
	}

	public DataFetcher<AddAuthorResultModel> addAuthor() {
		return env -> {
			try {
				AuthorInModel authorIn = mapper.convertValue(env.getArgument("author"), AuthorInModel.class);
				return addAuthorMutationResolver.addAuthor(authorIn);
			} catch (IllegalArgumentException e) {
				return null;
			}
		};
	}


}
