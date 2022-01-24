package org.people.weijuly.bookstore.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends CrudRepository<PurchaseEntity, String> {

    List<PurchaseEntity> findByCustomerId(String customerId);

    List<PurchaseEntity> findByCustomerIdAndBookId(String customerId, String bookId);

    List<PurchaseEntity> findByBookId(String bookId);
}
