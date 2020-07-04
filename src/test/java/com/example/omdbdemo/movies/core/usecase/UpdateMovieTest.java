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

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@UseCaseUnitTest
class UpdateMovieTest {

    UpdateMovie updateMovie;

    @MockBean
    MovieProvider movieProvider;

    @BeforeEach
    void setUp() {
        this.updateMovie = new UpdateMovie(movieProvider);
    }

    @Test
    @DisplayName("Should update movie")
    void updateMovieOk() throws NoSuchResourceException {
        // Arrange
        Movie alienWithAlexandreAstier = MovieFixture.getAlien()
                .withActors("Aleandre Astier");
        when(movieProvider.exists(MovieFixture.ALIEN_ID)).thenReturn(true);

        // Act
        updateMovie.execute(alienWithAlexandreAstier);

        // Assert
        verify(movieProvider).createOrUpdate(eq(alienWithAlexandreAstier));
    }

    @Test
    @DisplayName("Update unknown movie should throw no such resource exception")
    void updateMovieFail() {
        // Arrange
        String invalidId = "La moustache";
        Movie invalidMovie = MovieFixture.getAlien().withId(invalidId);

        when(movieProvider.exists(invalidId)).thenReturn(false);

        // Act + Assert
        assertThatThrownBy(() -> updateMovie.execute(invalidMovie))
                .isInstanceOf(NoSuchResourceException.class);

        verify(movieProvider, never()).createOrUpdate(any());
    }
}