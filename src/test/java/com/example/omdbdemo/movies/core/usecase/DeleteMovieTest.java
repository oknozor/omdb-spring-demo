package com.example.omdbdemo.movies.core.usecase;

import com.example.omdbdemo.common.core.exception.NoSuchResourceException;
import com.example.omdbdemo.config.annotation.UseCaseUnitTest;
import com.example.omdbdemo.movies.core.model.MovieFixture;
import com.example.omdbdemo.movies.core.port.MovieProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@UseCaseUnitTest
class DeleteMovieTest {

    DeleteMovie deleteMovie;

    @MockBean
    MovieProvider movieProvider;

    @BeforeEach
    void setUp() {
        this.deleteMovie = new DeleteMovie(movieProvider);
    }

    @Test
    @DisplayName("Should delete movie")
    void DeleteMovieOk() throws NoSuchResourceException {
        // Arrange
        String alienId = MovieFixture.ALIEN_ID;
        when(movieProvider.exists(alienId)).thenReturn(true);

        // Act
        deleteMovie.execute(alienId);

        // Assert
        verify(movieProvider).delete(eq(alienId));
    }

    @Test
    @DisplayName("Delete unknown movie should throw no such resource exception")
    void DeleteMovieFail() {
        // Arrange
        String invalidId = "La moustache";
        when(movieProvider.exists(invalidId)).thenReturn(false);

        // Act + Assert
        assertThatThrownBy(() -> deleteMovie.execute(invalidId))
                .isInstanceOf(NoSuchResourceException.class);

        verify(movieProvider, never()).delete(anyString());
    }
}