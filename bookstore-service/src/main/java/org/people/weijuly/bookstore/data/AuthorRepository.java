package org.people.weijuly.bookstore.data;

import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, String> {

    @NotNull
    @Cacheable(value = "author")
    List<AuthorEntity> findAll();

    @NotNull
    @Cacheable(value = "author")
    Optional<AuthorEntity> findById(@NotNull String id);

    @Cacheable(value = "author")
    Optional<AuthorEntity> findByFirstNameAndLastName(String firstName, String lastName);

    @Cacheable(value = "author")
    List<AuthorEntity> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);

    @NotNull
    @CacheEvict(value = "author", allEntries = true)
    @SuppressWarnings(value = {"unchecked"})
    AuthorEntity save(@NotNull AuthorEntity entity);

}
