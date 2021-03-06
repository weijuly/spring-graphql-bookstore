package org.people.weijuly.bookstore.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.people.weijuly.bookstore.data.AuthorEntity;
import org.people.weijuly.bookstore.data.AuthorRepository;
import org.people.weijuly.bookstore.model.AuthorInModel;
import org.people.weijuly.bookstore.model.AuthorModel;
import org.people.weijuly.bookstore.model.AuthorsModel;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    AuthorRepository authorRepository;

    @InjectMocks
    AuthorService authorService;

    private static final String FIRST_NAME = "Jules";
    private static final String LAST_NAME = "Verne";

    @Test
    @DisplayName("save should create an entry in AUTHOR table when entry doesn't exist")
    public void saveShouldCreateEntryWhenNotExists() {
        when(authorRepository.findByFirstNameAndLastName(FIRST_NAME, LAST_NAME))
                .thenReturn(Optional.empty());
        when(authorRepository.save(any(AuthorEntity.class))).thenReturn(authorEntity());
        AuthorModel authorModel = authorService.save(authorIn());
        assertNotNull(authorModel);
        assertEquals(authorModel.getFirstName(), FIRST_NAME);
        assertEquals(authorModel.getLastName(), LAST_NAME);
        assertNotNull(authorModel.getId());
        verify(authorRepository, times(1)).findByFirstNameAndLastName(FIRST_NAME, LAST_NAME);
        verify(authorRepository, times(1)).save(any(AuthorEntity.class));
    }

    @Test
    @DisplayName("save should return entry from AUTHOR table when entry exists")
    public void saveShouldReturnEntryWhenExists() {
        when(authorRepository.findByFirstNameAndLastName(FIRST_NAME, LAST_NAME))
                .thenReturn(Optional.of(authorEntity()));
        AuthorModel authorModel = authorService.save(authorIn());
        assertNotNull(authorModel);
        assertEquals(authorModel.getFirstName(), FIRST_NAME);
        assertEquals(authorModel.getLastName(), LAST_NAME);
        assertNotNull(authorModel.getId());
        verify(authorRepository, times(1)).findByFirstNameAndLastName(FIRST_NAME, LAST_NAME);
    }

    @Test
    @DisplayName("findByNameContains should return list of authors from AUTHOR table")
    public void findByNameContainsShouldReturnListOfAuthors() {
        when(authorRepository.findByFirstNameContainingOrLastNameContaining(FIRST_NAME, FIRST_NAME))
                .thenReturn(Collections.singletonList(authorEntity()));
        AuthorsModel authorsModel = authorService.findByNameContains(FIRST_NAME);
        assertAll(() -> {
            assertNotNull(authorsModel);
            assertNotNull(authorsModel.getAuthors());
            List<AuthorModel> authorModels = authorsModel.getAuthors();
            assertAll(() -> {
                assertFalse(authorModels.isEmpty());
                assertEquals(authorModels.size(), 1);
                AuthorModel authorModel = authorModels.get(0);
                assertAll(() -> {
                    assertEquals(authorModel.getLastName(), LAST_NAME);
                    assertEquals(authorModel.getFirstName(), FIRST_NAME);
                    assertNotNull(authorModel.getId());
                });
            });
        });
    }

    private AuthorInModel authorIn() {
        AuthorInModel authorIn = new AuthorInModel();
        authorIn.setFirstName(FIRST_NAME);
        authorIn.setLastName(LAST_NAME);
        return authorIn;
    }

    private AuthorEntity authorEntity() {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(UUID.randomUUID().toString());
        authorEntity.setFirstName(FIRST_NAME);
        authorEntity.setLastName(LAST_NAME);
        return authorEntity;
    }
}
