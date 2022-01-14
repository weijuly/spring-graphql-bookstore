package org.people.weijuly.bookstore.converter;

import org.people.weijuly.bookstore.data.AuthorEntity;
import org.people.weijuly.bookstore.model.AuthorInModel;
import org.people.weijuly.bookstore.model.AuthorModel;

public class AuthorConverter {

    private AuthorConverter() {
    }

    public static AuthorModel convert(AuthorEntity entity) {
        AuthorModel model = new AuthorModel();
        model.setId(entity.getId());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        return model;
    }

    public static AuthorEntity convert(AuthorModel model) {
        AuthorEntity entity = new AuthorEntity();
        entity.setId(model.getId());
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        return entity;
    }

    public static AuthorEntity convert(AuthorInModel model) {
        AuthorEntity entity = new AuthorEntity();
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        return entity;
    }
}
