package com.example.omdbdemo.movies.core.usecase;

import com.example.omdbdemo.common.core.exception.NoSuchResourceException;
import com.example.omdbdemo.common.core.exception.ResourceAlreadyExistsException;
import com.example.omdbdemo.config.annotation.UseCaseUnitTest;
import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.core.port.MovieFetcher;
import com.example.omdbdemo.movies.core.port.MovieProvider;
import com.example.omdbdemo.movies.dataproviders.db.entity.MovieFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@UseCaseUnitTest
class CreateMovieFromTitleTest {

    CreateMovieFromTitle createMovieFromTitle;

    @MockBean
    MovieProvider movieProvider;
    @MockBean
    MovieFetcher movieFetcher;

    @BeforeEach
    void setUp() {
        this.createMovieFromTitle = new CreateMovieFromTitle(movieProvider, movieFetcher);
    }

    @Test
    @DisplayName("Should create movie")
    void createMovieOk() throws NoSuchResourceException, ResourceAlreadyExistsException {
        // Arrange
        Movie alien = MovieFixture.getAlien();
        when(movieProvider.existByTitle("Alien")).thenReturn(false);
        when(movieFetcher.byTitle("Alien")).thenReturn(Optional.of(alien));
        when(movieProvider.createOrUpdate(any())).thenReturn(alien);

        // Act
        createMovieFromTitle.execute("Alien");

        // Assert
        verify(movieFetcher).byTitle(eq("Alien"));
        verify(movieProvider).createOrUpdate(eq(alien));
    }

    @Test
    @DisplayName("Should not create movie if it already exists in db")
    void createExistingMovieFails() {
        // Arrange
        Movie alien = MovieFixture.getAlien();
        when(movieProvider.existByTitle("Alien")).thenReturn(true);

        // Act + Assert
        assertThatThrownBy(() -> createMovieFromTitle.execute("Alien"))
                .isInstanceOf(ResourceAlreadyExistsException.class);
    }


    @Test
    @DisplayName("Should not create movie if not present in omdb api")
    void createMovieFail() {
        // Arrange
        String invalidTitle = "Invalid Title";
        when(movieFetcher.byTitle(invalidTitle)).thenReturn(Optional.empty());

        // Act + Assert
        assertThatThrownBy(() -> createMovieFromTitle.execute(invalidTitle))
                .isInstanceOf(NoSuchResourceException.class);

        verify(movieProvider, never()).createOrUpdate(any());
    }
}