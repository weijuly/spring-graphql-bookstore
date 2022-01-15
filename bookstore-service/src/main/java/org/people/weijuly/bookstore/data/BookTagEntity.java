package org.people.weijuly.bookstore.data;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "BOOK_TAGS")
public class BookTagEntity {

    @Id
    @Column(name = "ID")
    @GenericGenerator(name = "bookTagsId", strategy = "org.people.weijuly.bookstore.data.UUIDGenerator")
    @GeneratedValue(generator = "bookTagsId")
    private String id;

    @Column(name = "BOOK_ID", nullable = false)
    private String bookId;

    @Column(name = "TAG", nullable = false)
    private String tag;
}
