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
@Table(name = "AUTHOR")
public class AuthorEntity {

    @Id
    @Column(name = "ID")
    @GenericGenerator(name = "authorId", strategy = "org.people.weijuly.bookstore.data.UUIDGenerator")
    @GeneratedValue(generator = "authorId")
    private String id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

}
