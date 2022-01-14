package org.people.weijuly.bookstore.converter;

import org.people.weijuly.bookstore.data.AuthorEntity;
import org.people.weijuly.bookstore.data.BookEntity;
import org.people.weijuly.bookstore.model.BookInModel;
import org.people.weijuly.bookstore.model.BookModel;

import java.util.Collections;

public class BookConverter {

    public static BookEntity convert(BookInModel bookIn, String authorId) {
        BookEntity entity = new BookEntity();
        entity.setIsbn(bookIn.getIsbn());
        entity.setName(bookIn.getName());
        entity.setYear(bookIn.getYear());
        entity.setPages(bookIn.getPages());
        entity.setPrice(bookIn.getPrice());
        entity.setCopies(bookIn.getCopies());
        entity.setAuthorId(authorId);
        return entity;
    }

    public static BookModel convert(BookEntity bookEntity, AuthorEntity authorEntity) {
        BookModel model = new BookModel();
        model.setId(bookEntity.getId());
        model.setIsbn(bookEntity.getIsbn());
        model.setName(bookEntity.getName());
        model.setYear(bookEntity.getYear());
        model.setPrice(bookEntity.getPrice());
        model.setPages(bookEntity.getPages());
        model.setTags(Collections.emptyList());
        model.setUpdatedOn(bookEntity.getUpdatedOn().toString());
        model.setStats(null);
        model.setAuthor(AuthorConverter.convert(authorEntity));
        return model;
    }

}
