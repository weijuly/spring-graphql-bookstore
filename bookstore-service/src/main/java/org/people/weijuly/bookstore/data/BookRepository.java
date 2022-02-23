package org.people.weijuly.bookstore.data;

import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, String> {

    @NotNull
    @Cacheable(value = "book")
    Optional<BookEntity> findById(@NotNull String id);

    @Cacheable(value = "book")
    Optional<BookEntity> findByName(String name);

    @Cacheable(value = "book")
    List<BookEntity> findByAuthorId(String authorId);

    @Cacheable(value = "book")
    List<BookEntity> findByNameContaining(String name);

    @Cacheable(value = "book")
    List<BookEntity> findByYear(Integer year);

    @NotNull
    @SuppressWarnings(value = {"unchecked"})
    @CacheEvict(value = "book", allEntries = true)
    BookEntity save(@NotNull BookEntity entity);

}
