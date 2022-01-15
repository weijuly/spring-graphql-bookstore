package org.people.weijuly.bookstore.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {

    Optional<CustomerEntity> findByFirstNameAndLastName(String firstName, String lastName);
}
