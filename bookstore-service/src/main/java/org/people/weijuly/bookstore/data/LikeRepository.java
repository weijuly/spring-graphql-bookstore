package org.people.weijuly.bookstore.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends CrudRepository<LikeEntity, String> {

    Optional<LikeEntity> findByCustomerIdAndBookId(String customerId, String bookId);

    List<LikeEntity> findByCustomerId(String customerId);

    List<LikeEntity> findByBookId(String bookId);
}
