package org.people.weijuly.bookstore.data;

import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {

    @NotNull
    Optional<CustomerEntity> findById(@NotNull String id);

    @Cacheable(value = "customer")
    Optional<CustomerEntity> findByFirstNameAndLastName(String firstName, String lastName);

    @Cacheable(value = "customer")
    List<CustomerEntity> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);

    @NotNull
    @CacheEvict(value = "customer", allEntries = true)
    @SuppressWarnings(value = {"unchecked"})
    CustomerEntity save(@NotNull CustomerEntity entity);
}
