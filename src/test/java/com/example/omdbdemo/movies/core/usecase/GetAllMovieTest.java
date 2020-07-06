package com.example.omdbdemo.movies.core.usecase;

import com.example.omdbdemo.config.annotation.UseCaseUnitTest;
import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.core.model.MovieFixture;
import com.example.omdbdemo.movies.core.port.MovieProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.TimeZone;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@UseCaseUnitTest
class GetAllMovieTest {

    GetAllMovie getAllMovie;

    @MockBean
    MovieProvider movieProvider;

    @BeforeEach
    void setUp() {
        getAllMovie = new GetAllMovie(movieProvider);
    }

    @Test
    @DisplayName("Should get all movie")
    void getAllOK() {
        // Arrange
        when(movieProvider.getAll()).thenReturn(List.of(MovieFixture.getAlien(), MovieFixture.getPrincessMononoke()));

        // Act
        List<Movie> allMovies = getAllMovie.execute();

        // Assert
        assertThat(allMovies).extracting("id").containsExactlyInAnyOrder(MovieFixture.ALIEN_ID, MovieFixture.MONONOKE_ID);
    }

}