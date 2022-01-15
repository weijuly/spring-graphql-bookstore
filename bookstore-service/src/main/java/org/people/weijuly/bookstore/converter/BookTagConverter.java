package org.people.weijuly.bookstore.converter;

import org.people.weijuly.bookstore.data.BookTagEntity;
import org.people.weijuly.bookstore.model.TagModel;

public class BookTagConverter {

    public static TagModel convert(BookTagEntity bookTagEntity) {
        return TagModel.valueOf(bookTagEntity.getTag());
    }

    public static BookTagEntity convert(TagModel tagModel, String bookId) {
        BookTagEntity bookTagEntity = new BookTagEntity();
        bookTagEntity.setBookId(bookId);
        bookTagEntity.setTag(tagModel.name());
        return bookTagEntity;
    }
}
