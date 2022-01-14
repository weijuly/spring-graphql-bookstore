package org.people.weijuly.bookstore.data;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "BOOK")
public class BookEntity {

    @Id
    @Column(name = "ID")
    @GenericGenerator(name = "bookId", strategy = "org.people.weijuly.bookstore.data.UUIDGenerator")
    @GeneratedValue(generator = "bookId")
    private String id;

    @Column(name = "ISBN", nullable = false)
    private String isbn;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PUB_YEAR", nullable = false)
    private Integer year;

    @Column(name = "PAGES", nullable = false)
    private Integer pages;

    @Column(name = "PRICE", nullable = false)
    private Integer price;

    @Column(name = "COPIES", nullable = false)
    private Integer copies;

    @Column(name = "UPDATED_ON", nullable = false)
    private Date updatedOn;

    @Column(name = "AUTHOR_ID", nullable = false)
    private String author;

    @PrePersist
    @PreUpdate
    public void setUpdatedOn() {
        updatedOn = new Date();
    }
}
