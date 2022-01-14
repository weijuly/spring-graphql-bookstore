package org.people.weijuly.bookstore.graphql;

import graphql.schema.TypeResolver;
import org.people.weijuly.bookstore.graphql.impl.type.AddAuthorResultResolver;
import org.springframework.stereotype.Component;

@Component
public class GraphQLTypeResolvers {

	public TypeResolver addAuthorResultResolver() {
		return new AddAuthorResultResolver();
	}
}
