package com.example.omdbdemo.movies.dataproviders.db.repository;

import com.example.omdbdemo.comments.core.model.CommentFixture;
import com.example.omdbdemo.common.core.exception.NoSuchResourceException;
import com.example.omdbdemo.config.SharedPostgresqlContainer;
import com.example.omdbdemo.config.annotation.DatabaseTest;
import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.core.model.MovieFixture;
import com.example.omdbdemo.movies.core.model.MovieRanking;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DatabaseTest
@Import({MovieDatabaseProvider.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MovieDatabaseProviderDatabaseTest {

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = SharedPostgresqlContainer.getInstance();

    @Autowired
    MovieDatabaseProvider movieDatabaseProvider;

    @Test
    @DisplayName("Should get all movies rankings from last week")
    @Order(1)
    void getAllByCommentCountAndDateIntervalOk() {
        // Arrange

        // Act
        List<MovieRanking> rankings = movieDatabaseProvider.getRankingsWithInterval(LocalDate.now().minusDays(7), LocalDate.now());

        // Assert
        assertThat(rankings).extracting("movieId").containsExactly(MovieFixture.ALIEN_ID);
        assertThat(rankings).extracting("totalComments").containsExactly(1);
        assertThat(rankings).extracting("rank").containsExactly(1);
    }

    @Test
    @DisplayName("Should get all movies rankings ")
    @Order(1)
    void getAllByCommentCountOk() {
        // Arrange

        // Alien has 1 comment
        // Interstelar has 1 comment


        // Mononoke has more comments
        String princessMononokeId = "Princess Mononoke";
        movieDatabaseProvider.createOrUpdate(MovieFixture.getPrincessMononoke()
                .withId(princessMononokeId)
                .withComments(List.of(
                        CommentFixture.getCommentWithMovieId(princessMononokeId),
                        CommentFixture.getCommentWithMovieId(princessMononokeId),
                        CommentFixture.getCommentWithMovieId(princessMononokeId),
                        CommentFixture.getCommentWithMovieId(princessMononokeId),
                        CommentFixture.getCommentWithMovieId(princessMononokeId),
                        CommentFixture.getCommentWithMovieId(princessMononokeId)
                )));

        List<MovieRanking> rankings = movieDatabaseProvider.getRankings();

        // Assert
        assertThat(rankings).extracting("movieId").contains("Princess Mononoke", MovieFixture.ALIEN_ID, MovieFixture.INTERSTELLAR_ID);
        assertThat(rankings).extracting("totalComments").containsExactly(6, 1, 1);
        assertThat(rankings).extracting("rank").containsExactly(1, 2, 2);
    }

    @Test
    @DisplayName("Should get all movies")
    @Order(1)
    void getAllOk() {
        // Act
        List<Movie> movies = movieDatabaseProvider.getAll();

        // Assert
        // tests are run asynchronously, there might be more than one movie in the container db
        assertThat(movies).hasSize(3);
        assertThat(movies.stream().map(Movie::getTitle)).contains("Alien");
    }

    @Test
    @DisplayName("Should create movie")
    @Order(2)
    void createOk() {
        // Arrange
        Movie mononokeHime = MovieFixture.getPrincessMononoke();

        // Act
        Movie createdMovie = movieDatabaseProvider.createOrUpdate(mononokeHime);

        // Assert
        assertThat(createdMovie.getId()).isEqualTo(MovieFixture.MONONOKE_ID);
        assertThat(createdMovie.getTitle()).isEqualTo("Mononoke Hime");
        assertThat(createdMovie.getRelease()).isEqualTo("1994");
        assertThat(createdMovie.getLanguage()).isEqualTo("Japanese");
        assertThat(createdMovie.getRuntime()).isEqualTo("2H");
        assertThat(createdMovie.getWriter()).isEqualTo("Miyazaki");
        assertThat(createdMovie.getCountry()).isEqualTo("Japan");
        assertThat(createdMovie.getGenre()).isEqualTo("Japanese Animation");
        assertThat(createdMovie.getPlot()).isEqualTo("Prince Ashitaka on a journey to heal is cursed wound");
        assertThat(createdMovie.getDirector()).isEqualTo("Ayaho Miyazaki");
        assertThat(createdMovie.getActors()).isEqualTo("Brad Pit");
        assertThat(createdMovie.getAwards()).isEqualTo("Best animation movie all of time");
    }

    @Test
    @DisplayName("Should get movie by id")
    @Order(2)
    void byIdOk() {
        // Arrange
        String alienId = MovieFixture.ALIEN_ID;

        // Act
        Optional<Movie> alien = movieDatabaseProvider.byId(alienId);

        // Assert
        assertThat(alien).isPresent();
        assertThat(alien.get().getTitle()).isEqualTo("Alien");
    }

    @Test
    @DisplayName("Should not get movie when movie is not present")
    @Order(2)
    void byIdFails() {
        // Arrange
        String invalidId = "Alien 4";

        // Act
        Optional<Movie> alien = movieDatabaseProvider.byId(invalidId);

        // Assert
        assertThat(alien).isNotPresent();
    }


    @Test
    @DisplayName("Should update movie")
    @Order(2)
    void updateOk() {
        // Arrange
        Movie alienFrenchEdit = MovieFixture.getAlien()
                .withActors("Gérard Depardieu");
        // Act
        Movie alienUpdated = movieDatabaseProvider.createOrUpdate(alienFrenchEdit);

        // Assert
        assertThat(alienUpdated.getActors()).contains("Gérard Depardieu");
    }

    @Test
    @DisplayName("Alien the movie should exist")
    @Order(2)
    void existByIdOk() throws NoSuchResourceException {
        // Arrange
        String alienId = MovieFixture.ALIEN_ID;

        // Act
        boolean exist = movieDatabaseProvider.exists(alienId);

        // Assert
        assertThat(exist).isTrue();
    }

    @Test
    @DisplayName("'La moustache'  the movie should not exist")
    @Order(2)
    void existByIdFail() {
        // Arrange
        String invalidId = "La moustache";

        // Act
        boolean exist = movieDatabaseProvider.exists(invalidId);

        // Assert
        assertThat(exist).isFalse();
    }

    @Test
    @DisplayName("Should delete movie")
    @Order(2)
    void deleteOk() {
        // Arrange
        String alienId = MovieFixture.ALIEN_ID;

        // Act
        movieDatabaseProvider.delete(alienId);

        // Assert
        assertThat(movieDatabaseProvider.byId(alienId)).isNotPresent();
    }
}