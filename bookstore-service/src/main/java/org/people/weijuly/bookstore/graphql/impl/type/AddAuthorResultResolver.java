package org.people.weijuly.bookstore.graphql.impl.type;

import graphql.TypeResolutionEnvironment;
import graphql.schema.GraphQLObjectType;
import graphql.schema.TypeResolver;
import org.people.weijuly.bookstore.graphql.BookStoreDataFetchers;
import org.people.weijuly.bookstore.model.AddAuthorErrorModel;
import org.people.weijuly.bookstore.model.AuthorModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddAuthorResultResolver implements TypeResolver {

	private static final Logger logger = LoggerFactory.getLogger(BookStoreDataFetchers.class);

	@Override
	public GraphQLObjectType getType(TypeResolutionEnvironment env) {
		if (env.getObject() instanceof AuthorModel) {
			logger.info("AddAuthorResultResolver: resolved to AddAuthorError");
			return env.getSchema().getObjectType("Author");
		} else if (env.getObject() instanceof AddAuthorErrorModel) {
			logger.info("AddAuthorResultResolver: resolved to AddAuthorError");
			return env.getSchema().getObjectType("AddAuthorError");
		} else {
			logger.info("AddAuthorResultResolver: cannot resolve");
			return null;
		}
	}

}
