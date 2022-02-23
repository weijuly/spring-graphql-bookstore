package org.people.weijuly.bookstore.data;

import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends CrudRepository<PurchaseEntity, String> {

    @Cacheable(value = "purchase")
    List<PurchaseEntity> findByCustomerId(String customerId);

    @Cacheable(value = "purchase")
    List<PurchaseEntity> findByCustomerIdAndBookId(String customerId, String bookId);

    @Cacheable(value = "purchase")
    List<PurchaseEntity> findByBookId(String bookId);

    @NotNull
    @CacheEvict(value = "purchase", allEntries = true)
    @SuppressWarnings(value = {"unchecked"})
    PurchaseEntity save(@NotNull PurchaseEntity entity);
}
