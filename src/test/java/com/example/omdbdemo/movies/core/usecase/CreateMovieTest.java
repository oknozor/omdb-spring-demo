package com.example.omdbdemo.movies.core.usecase;

import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.core.port.MovieProvider;
import com.example.omdbdemo.movies.dataproviders.db.entity.MovieFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CreateMovieTest {

    CreateMovie createMovie;

    @MockBean
    MovieProvider movieProvider;

    @BeforeEach
    void setUp() {
        this.createMovie = new CreateMovie(movieProvider);
    }

    @Test
    @DisplayName("Should create movie")
    void CreateMovieOk() {
        // Arrange
        Movie alien = MovieFixture.getAlien();
        when(movieProvider.createOrUpdate(any())).thenReturn(alien);

        // Act
        Movie createdMovie = createMovie.execute(alien);

        // Assert
        verify(movieProvider).createOrUpdate(eq(alien));
    }
}