package org.people.weijuly.bookstore.data;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.people.weijuly.bookstore.util.LendingStatus;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Entity
@Data
@Table(name = "LENDING")
public class LendingEntity {

    @Id
    @Column(name = "ID")
    @GenericGenerator(name = "lendingId", strategy = "org.people.weijuly.bookstore.data.UUIDGenerator")
    @GeneratedValue(generator = "lendingId")
    private String id;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private String customerId;

    @Column(name = "BOOK_ID", nullable = false)
    private String bookId;

    @Column(name = "DUE_ON", nullable = false)
    private Date dueOn;

    @Column(name = "STATUS", nullable = false)
    @Convert(converter = LendingStatusConverter.class)
    private LendingStatus status;

    @PrePersist
    public void setDueOn() {
        dueOn = Date.from(Instant.now().plus(30L, ChronoUnit.DAYS));
    }
}
