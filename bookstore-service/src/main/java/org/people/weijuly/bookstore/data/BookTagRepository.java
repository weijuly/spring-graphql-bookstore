package org.people.weijuly.bookstore.data;

import org.people.weijuly.bookstore.model.TagModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookTagRepository extends CrudRepository<BookTagEntity, String> {

    List<BookTagEntity> findByBookId(String bookId);

    List<BookTagEntity> findByTag(String tag);
}
