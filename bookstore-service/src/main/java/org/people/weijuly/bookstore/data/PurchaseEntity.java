package org.people.weijuly.bookstore.data;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "PURCHASE")
public class PurchaseEntity {

    @Id
    @Column(name = "ID")
    @GenericGenerator(name = "purchaseId", strategy = "org.people.weijuly.bookstore.data.UUIDGenerator")
    @GeneratedValue(generator = "purchaseId")
    private String id;

    @Column(name = "BOOK_ID", nullable = false)
    private String bookId;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private String customerId;

    @Column(name = "PURCHASED_ON", nullable = false)
    private Date purchasedOn;

    @PrePersist
    public void setPurchasedOn() {
        purchasedOn = new Date();
    }
}
