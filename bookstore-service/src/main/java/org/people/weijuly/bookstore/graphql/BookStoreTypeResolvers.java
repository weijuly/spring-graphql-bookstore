package org.people.weijuly.bookstore.graphql;

import graphql.TypeResolutionEnvironment;
import graphql.schema.GraphQLObjectType;
import graphql.schema.TypeResolver;
import org.people.weijuly.bookstore.model.AuthorModel;
import org.people.weijuly.bookstore.model.AuthorsModel;
import org.people.weijuly.bookstore.model.BookModel;
import org.people.weijuly.bookstore.model.BooksModel;
import org.people.weijuly.bookstore.model.CustomerModel;
import org.people.weijuly.bookstore.model.CustomersModel;
import org.springframework.stereotype.Component;

import static org.people.weijuly.bookstore.util.BookStoreConstants.AuthorType;
import static org.people.weijuly.bookstore.util.BookStoreConstants.AuthorsType;
import static org.people.weijuly.bookstore.util.BookStoreConstants.BookStoreErrorType;
import static org.people.weijuly.bookstore.util.BookStoreConstants.BookType;
import static org.people.weijuly.bookstore.util.BookStoreConstants.BooksType;
import static org.people.weijuly.bookstore.util.BookStoreConstants.CustomerType;
import static org.people.weijuly.bookstore.util.BookStoreConstants.CustomersType;

@Component
public class BookStoreTypeResolvers {

    public TypeResolver authorResultTypeResolver() {
        return env -> env.getObject() instanceof AuthorModel ? getType(env, AuthorType) : getErrorType(env);
    }

    public TypeResolver authorsResultTypeResolver() {
        return env -> env.getObject() instanceof AuthorsModel ? getType(env, AuthorsType) : getErrorType(env);
    }

    public TypeResolver bookResultTypeResolver() {
        return env -> env.getObject() instanceof BookModel ? getType(env, BookType) : getErrorType(env);
    }

    public TypeResolver booksResultTypeResolver() {
        return env -> env.getObject() instanceof BooksModel ? getType(env, BooksType) : getErrorType(env);
    }

    public TypeResolver customerResultTypeResolver() {
        return env -> env.getObject() instanceof CustomerModel ? getType(env, CustomerType) : getErrorType(env);
    }

    public TypeResolver customersResultTypeResolver() {
        return env -> env.getObject() instanceof CustomersModel ? getType(env, CustomersType) : getErrorType(env);
    }

    private GraphQLObjectType getType(TypeResolutionEnvironment env, String type) {
        return env.getSchema().getObjectType(type);
    }

    private GraphQLObjectType getErrorType(TypeResolutionEnvironment env) {
        return env.getSchema().getObjectType(BookStoreErrorType);
    }

}
