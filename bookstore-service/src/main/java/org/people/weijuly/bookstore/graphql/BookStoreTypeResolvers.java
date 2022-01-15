package org.people.weijuly.bookstore.graphql;

import graphql.TypeResolutionEnvironment;
import graphql.schema.GraphQLObjectType;
import graphql.schema.TypeResolver;
import org.people.weijuly.bookstore.model.BookModel;
import org.people.weijuly.bookstore.model.CustomerModel;
import org.springframework.stereotype.Component;

import static org.people.weijuly.bookstore.util.BookStoreConstants.BookStoreErrorType;
import static org.people.weijuly.bookstore.util.BookStoreConstants.BookType;
import static org.people.weijuly.bookstore.util.BookStoreConstants.CustomerType;

@Component
public class BookStoreTypeResolvers {

    public TypeResolver addBookResultResolver() {
        return env -> env.getObject() instanceof BookModel ? getType(env, BookType) : getErrorType(env);
    }

    public TypeResolver addCustomerResultResolver() {
        return env -> env.getObject() instanceof CustomerModel ? getType(env, CustomerType) : getErrorType(env);
    }

    private GraphQLObjectType getType(TypeResolutionEnvironment env, String type) {
        return env.getSchema().getObjectType(type);
    }

    private GraphQLObjectType getErrorType(TypeResolutionEnvironment env) {
        return env.getSchema().getObjectType(BookStoreErrorType);
    }
}
