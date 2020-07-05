package com.example.omdbdemo.movies.core.usecase;

import com.example.omdbdemo.config.annotation.UseCaseUnitTest;
import com.example.omdbdemo.movies.core.model.MovieFixture;
import com.example.omdbdemo.movies.core.model.MovieRanking;
import com.example.omdbdemo.movies.core.port.MovieProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@UseCaseUnitTest
class GetMovieRankingsTest {
    GetMovieRankings getMovieRankings;

    @MockBean
    MovieProvider movieProvider;

    @BeforeEach
    void setUp() {
        getMovieRankings = new GetMovieRankings(movieProvider);
    }

    @Test
    @DisplayName("Should get movie rankings between two date")
    void rankingsWithIntervalOk() {
        // Arrange
        LocalDate from = LocalDate.now().minusDays(1);
        LocalDate two = LocalDate.now();
        when(movieProvider.getRankingsWithInterval(from, two)).thenReturn(MovieFixture.rankings());

        // Act
        List<MovieRanking> rankings = getMovieRankings.execute(from, two);

        // Assert
        assertThat(rankings).extracting("movieId").containsExactly(MovieFixture.MONONOKE_ID, MovieFixture.ALIEN_ID);
        assertThat(rankings).extracting("totalComments").containsExactly(12, 5);
        assertThat(rankings).extracting("rank").containsExactly(1, 2);

    }
}