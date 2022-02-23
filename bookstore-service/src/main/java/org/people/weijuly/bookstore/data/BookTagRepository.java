package org.people.weijuly.bookstore.data;

import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookTagRepository extends CrudRepository<BookTagEntity, String> {

    @Cacheable(value = "book_tag")
    List<BookTagEntity> findByBookId(String bookId);

    @Cacheable(value = "book_tag")
    List<BookTagEntity> findByTag(String tag);

    @NotNull
    @CacheEvict(value = "author", allEntries = true)
    @SuppressWarnings(value = {"unchecked"})
    BookTagEntity save(@NotNull BookTagEntity entity);
}
