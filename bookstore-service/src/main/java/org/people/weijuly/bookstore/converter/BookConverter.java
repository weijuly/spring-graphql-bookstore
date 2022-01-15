package org.people.weijuly.bookstore.converter;

import org.people.weijuly.bookstore.data.AuthorEntity;
import org.people.weijuly.bookstore.data.BookEntity;
import org.people.weijuly.bookstore.data.BookTagEntity;
import org.people.weijuly.bookstore.model.BookInModel;
import org.people.weijuly.bookstore.model.BookModel;
import org.people.weijuly.bookstore.model.TagModel;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

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

    public static BookModel convert(BookEntity bookEntity, AuthorEntity authorEntity,
                                    List<BookTagEntity> bookTagEntities) {
        BookModel model = new BookModel();
        model.setId(bookEntity.getId());
        model.setIsbn(bookEntity.getIsbn());
        model.setName(bookEntity.getName());
        model.setYear(bookEntity.getYear());
        model.setPrice(bookEntity.getPrice());
        model.setPages(bookEntity.getPages());
        model.setTags(tags(bookTagEntities));
        model.setUpdatedOn(bookEntity.getUpdatedOn().toString());
        model.setUpdatedOn(new SimpleDateFormat("yyyy-MM-dd").format(bookEntity.getUpdatedOn()));
        model.setStats(null);
        model.setAuthor(AuthorConverter.convert(authorEntity));
        return model;
    }

    private static List<TagModel> tags(List<BookTagEntity> bookTagEntities) {
        return bookTagEntities
                .stream()
                .map(BookTagConverter::convert)
                .collect(Collectors.toList());
    }

}
