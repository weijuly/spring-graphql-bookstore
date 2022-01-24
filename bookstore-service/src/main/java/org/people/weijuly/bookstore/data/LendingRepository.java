package org.people.weijuly.bookstore.data;

import org.people.weijuly.bookstore.util.LendingStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LendingRepository extends CrudRepository<LendingEntity, String> {

    List<LendingEntity> findByCustomerId(String customerId);

    Optional<LendingEntity> findByCustomerIdAndBookIdAndStatus(String customerId, String bookId, LendingStatus status);

    List<LendingEntity> findByCustomerIdAndBookId(String customerId, String bookId);

    List<LendingEntity> findByBookIdAndStatus(String bookId, LendingStatus status);

}
