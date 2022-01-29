package org.people.weijuly.bookstore.graphql.impl.subscription;

import org.people.weijuly.bookstore.model.BookModel;
import org.people.weijuly.bookstore.service.BookService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Component
public class BooksSubscriptionResolverImpl {

    @Autowired
    BookService bookService;

    public Publisher<BookModel> books() throws Exception {
        return Flux.zip(
                Flux.interval(Duration.ofSeconds(1L)),
                Flux.fromStream(bookService.allBooks()),
                (x, y) -> y
        );
    }
}
