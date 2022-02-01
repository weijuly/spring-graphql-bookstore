package org.people.weijuly.bookstore.graphql;

import graphql.TypeResolutionEnvironment;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.TypeResolver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.people.weijuly.bookstore.model.AuthorModel;
import org.people.weijuly.bookstore.model.BookStoreErrorModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.people.weijuly.bookstore.util.BookStoreConstants.AuthorType;
import static org.people.weijuly.bookstore.util.BookStoreConstants.BookStoreErrorType;

@ExtendWith(MockitoExtension.class)
class BookStoreTypeResolversTest {

    BookStoreTypeResolvers bookStoreTypeResolvers = new BookStoreTypeResolvers();

    TypeResolutionEnvironment environment;
    GraphQLSchema schema;
    GraphQLObjectType objectType;

    private void setup(String typeName, Object typeObject) {
        environment = mock(TypeResolutionEnvironment.class);
        schema = mock(GraphQLSchema.class);
        objectType = mock(GraphQLObjectType.class);
        when(environment.getObject()).thenReturn(typeObject);
        when(environment.getSchema()).thenReturn(schema);
        when(schema.getObjectType(typeName)).thenReturn(objectType);
        when(objectType.getName()).thenReturn(typeObject.getClass().getSimpleName());
    }

    @Test
    @DisplayName("authorResultTypeResolver should return AuthorModel class name for type AuthorModel")
    public void authorResultTypeResolverShouldResolveAuthorType() {
        setup(AuthorType, new AuthorModel());
        TypeResolver typeResolver = bookStoreTypeResolvers.authorResultTypeResolver();
        GraphQLObjectType objectType = typeResolver.getType(environment);
        assertEquals(objectType.getName(), AuthorModel.class.getSimpleName());
    }

    @Test
    @DisplayName("authorResultTypeResolver should return BookStoreErrorModel class name for type BookStoreErrorModel")
    public void authorResultTypeResolverShouldResolveErrorType() {
        setup(BookStoreErrorType, new BookStoreErrorModel());
        TypeResolver typeResolver = bookStoreTypeResolvers.authorResultTypeResolver();
        GraphQLObjectType objectType = typeResolver.getType(environment);
        assertEquals(objectType.getName(), BookStoreErrorModel.class.getSimpleName());
    }
}
