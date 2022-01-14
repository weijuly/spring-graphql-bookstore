package org.people.weijuly.bookstore.data;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, String> {

	@NotNull
	List<AuthorEntity> findAll();

	Optional<AuthorEntity> findByFirstNameAndLastName(String firstName, String lastName);
}
