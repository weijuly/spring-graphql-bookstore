package org.people.weijuly.bookstore.graphql;

import graphql.schema.DataFetcher;
import org.people.weijuly.bookstore.graphql.impl.subscription.BooksSubscriptionResolverImpl;
import org.people.weijuly.bookstore.model.BookModel;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BooksStoreSubscriptionDataFetchers {

    @Autowired
    BooksSubscriptionResolverImpl booksSubscriptionResolver;

    public DataFetcher<Publisher<BookModel>> books() {
        return env -> booksSubscriptionResolver.books();
    }
}
