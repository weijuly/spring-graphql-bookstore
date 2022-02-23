package org.people.weijuly.bookstore.data;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "BOOK_LIKE")
public class LikeEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GenericGenerator(name = "likeId", strategy = "org.people.weijuly.bookstore.data.UUIDGenerator")
    @GeneratedValue(generator = "likeId")
    private String id;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private String customerId;

    @Column(name = "BOOK_ID", nullable = false)
    private String bookId;
}
