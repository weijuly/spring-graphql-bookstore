package org.people.weijuly.bookstore.data;

import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends CrudRepository<LikeEntity, String> {

    @Cacheable(value = "book_like")
    Optional<LikeEntity> findByCustomerIdAndBookId(String customerId, String bookId);

    @Cacheable(value = "book_like")
    List<LikeEntity> findByCustomerId(String customerId);

    @Cacheable(value = "book_like")
    List<LikeEntity> findByBookId(String bookId);

    @NotNull
    @CacheEvict(value = "book_like", allEntries = true)
    @SuppressWarnings(value = {"unchecked"})
    LikeEntity save(@NotNull LikeEntity entity);
}
