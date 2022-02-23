package org.people.weijuly.bookstore.data;

import org.jetbrains.annotations.NotNull;
import org.people.weijuly.bookstore.util.LendingStatus;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LendingRepository extends CrudRepository<LendingEntity, String> {

    @Cacheable(value = "lending")
    List<LendingEntity> findByCustomerId(String customerId);

    @Cacheable(value = "lending")
    Optional<LendingEntity> findByCustomerIdAndBookIdAndStatus(String customerId, String bookId, LendingStatus status);

    @Cacheable(value = "lending")
    List<LendingEntity> findByCustomerIdAndBookId(String customerId, String bookId);

    @Cacheable(value = "lending")
    List<LendingEntity> findByBookIdAndStatus(String bookId, LendingStatus status);

    @NotNull
    @CacheEvict(value = "lending", allEntries = true)
    @SuppressWarnings(value = {"unchecked"})
    LendingEntity save(@NotNull LendingEntity entity);
}
