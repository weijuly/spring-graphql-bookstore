package org.people.weijuly.bookstore.graphql.impl.query;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.people.weijuly.bookstore.data.AuthorEntity;
import org.people.weijuly.bookstore.data.AuthorRepository;
import org.people.weijuly.bookstore.model.AuthorModel;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorsQueryResolverImplTest {

	@Mock
	AuthorRepository authorRepository;

	@InjectMocks
	AuthorsQueryResolverImpl impl;

	@Test
	public void test() throws Exception {
		when(authorRepository.findAll()).thenReturn(singletonList(new AuthorEntity()));
		List<AuthorModel> models = impl.authors();
		assertNotNull(models);
		assertEquals(models.size(), 1);
	}
}
