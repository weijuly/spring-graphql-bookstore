package org.people.weijuly.bookstore.converter;

import org.junit.jupiter.api.Test;
import org.people.weijuly.bookstore.data.AuthorEntity;
import org.people.weijuly.bookstore.model.AuthorInModel;
import org.people.weijuly.bookstore.model.AuthorModel;

import static org.junit.jupiter.api.Assertions.*;

class AuthorConverterTest {

	@Test
	public void convertModelToEntity() {
		AuthorModel model = new AuthorModel();
		model.setId("id");
		model.setFirstName("firstName");
		model.setLastName("lastName");
		AuthorEntity entity = AuthorConverter.convert(model);
		assertNotNull(entity);
		assertEquals(entity.getId(), model.getId());
		assertEquals(entity.getFirstName(), model.getFirstName());
		assertEquals(entity.getLastName(), model.getLastName());
	}

	@Test
	public void convertEntityToModel() {
		AuthorEntity entity = new AuthorEntity();
		entity.setId("id");
		entity.setFirstName("firstName");
		entity.setLastName("lastName");
		AuthorModel model = AuthorConverter.convert(entity);
		assertNotNull(model);
		assertEquals(model.getId(), entity.getId());
		assertEquals(model.getFirstName(), entity.getFirstName());
		assertEquals(model.getLastName(), entity.getLastName());
	}

	@Test
	public void convertModelInToEntity() {
		AuthorInModel model = new AuthorInModel();
		model.setFirstName("firstName");
		model.setLastName("lastName");
		AuthorEntity entity = AuthorConverter.convert(model);
		assertNotNull(entity);
		assertNull(entity.getId());
		assertEquals(entity.getFirstName(), model.getFirstName());
		assertEquals(entity.getLastName(), model.getLastName());
	}

}
