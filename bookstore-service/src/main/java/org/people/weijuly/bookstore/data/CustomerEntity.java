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
@Table(name = "CUSTOMER")
public class CustomerEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GenericGenerator(name = "customerId", strategy = "org.people.weijuly.bookstore.data.UUIDGenerator")
    @GeneratedValue(generator = "customerId")
    private String id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
}
