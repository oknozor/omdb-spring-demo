package com.example.omdbdemo.movies.core.usecase;

import com.example.omdbdemo.common.core.exception.NoSuchResourceException;
import com.example.omdbdemo.config.annotation.UseCaseUnitTest;
import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.core.port.MovieProvider;
import com.example.omdbdemo.movies.dataproviders.db.entity.MovieFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@UseCaseUnitTest
class GetMovieTest {

    GetMovie getMovie;

    @MockBean
    MovieProvider movieProvider;

    @BeforeEach
    void setUp() {
        this.getMovie = new GetMovie(movieProvider);
    }

    @Test
    @DisplayName("Should get movie by its id")
    void setGetMovieOk() throws NoSuchResourceException {
        // Arrange
        Movie alien = MovieFixture.getAlien();
        when(movieProvider.byId(any())).thenReturn(Optional.of(alien));

        // Act
        Movie movie = getMovie.execute(alien.getId());

        // Assert
        verify(movieProvider).byId(eq(alien.getId()));
        assertThat(movie.getTitle()).isEqualTo("Alien");
    }


    @Test
    @DisplayName("Should thrown no such resource when movie is not present")
    void getMovieFails() throws NoSuchResourceException {
        // Arrange
        String invalidId = "La moustache";
        when(movieProvider.byId(any())).thenReturn(Optional.empty());

        // Act + Assert
        assertThatThrownBy(() -> getMovie.execute(invalidId)).isInstanceOf(NoSuchResourceException.class);
    }
}