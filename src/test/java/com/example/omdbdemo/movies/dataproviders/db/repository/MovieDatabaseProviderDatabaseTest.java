package com.example.omdbdemo.movies.dataproviders.db.repository;

import com.example.omdbdemo.comments.core.model.CommentFixture;
import com.example.omdbdemo.common.core.exception.NoSuchResourceException;
import com.example.omdbdemo.config.SharedPostgresqlContainer;
import com.example.omdbdemo.config.annotation.DatabaseTest;
import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.core.model.MovieFixture;
import com.example.omdbdemo.movies.core.model.MovieRanking;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
class MovieDatabaseProviderDatabaseTest {

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = SharedPostgresqlContainer.getInstance();

    @Autowired
    MovieDatabaseProvider movieDatabaseProvider;

    @Test
    @DisplayName("Should create movie")
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
    @DisplayName("Should delete movie")
    void deleteOk() {
        // Arrange
        String alienId = MovieFixture.ALIEN_ID;

        // Act
        movieDatabaseProvider.delete(alienId);

        // Assert
        assertThat(movieDatabaseProvider.byId(alienId)).isNotPresent();
    }

    @Test
    @DisplayName("Alien the movie should exist")
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
    void existByIdFail() {
        // Arrange
        String invalidId = "La moustache";

        // Act
        boolean exist = movieDatabaseProvider.exists(invalidId);

        // Assert
        assertThat(exist).isFalse();
    }

    @Test
    @DisplayName("Should get all movies")
    void getAllOk() {
        // Act
        List<Movie> movies = movieDatabaseProvider.getAll();

        // Assert
        // tests are run asynchronously, there might be more than one movie in the container db
        assertThat(movies).hasSizeGreaterThan(1);
        assertThat(movies.stream().map(Movie::getTitle)).contains("Alien");
    }

    @Test
    @DisplayName("Should get all movies rankings ")
    void getAllByCommentCountOk() {
        // Arrange
        // Alien 1 has One Comment
        // Alien 2 has Two Comments
        String alien2Id = "Alien 2";
        movieDatabaseProvider.createOrUpdate(MovieFixture.getAlien()
                .withId(alien2Id)
                .withComments(List.of(
                        CommentFixture.getCommentWithMovieId(alien2Id),
                        CommentFixture.getCommentWithMovieId(alien2Id))));

        // Alien 3 has Three Comments
        String alien3ID = "Alien 3";
        movieDatabaseProvider.createOrUpdate(MovieFixture.getAlien()
                .withId(alien3ID)
                .withComments(List.of(
                        CommentFixture.getCommentWithMovieId(alien3ID),
                        CommentFixture.getCommentWithMovieId(alien3ID),
                        CommentFixture.getCommentWithMovieId(alien3ID))));

        // Alien 4 has Three Comments
        String alien4Id = "Alien 4";
        movieDatabaseProvider.createOrUpdate(MovieFixture.getAlien()
                .withId(alien4Id)
                .withComments(List.of(
                        CommentFixture.getCommentWithMovieId(alien4Id),
                        CommentFixture.getCommentWithMovieId(alien4Id),
                        CommentFixture.getCommentWithMovieId(alien4Id))));

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

        // Act
        List<MovieRanking> rankings = movieDatabaseProvider.getRankings();

        // Assert
        assertThat(rankings).extracting("movieId").contains("Princess Mononoke", "Alien 4", "Alien 3", "Alien 2", MovieFixture.ALIEN_ID);
        assertThat(rankings).extracting("totalComments").containsExactly(6, 3, 3, 2, 2);
        assertThat(rankings).extracting("rank").containsExactly(1, 2, 2, 3, 3);
    }

    @Test
    @DisplayName("Should get all movies rankings from last week")
    void getAllByCommentCountAndDateIntervalOk() {
        // Arrange
        // Creation time stamp are read only, we are using comments for alien
        // From the afterMigrate script here, one from today and one from last year

        // Act
        List<MovieRanking> rankings = movieDatabaseProvider.getRankingsWithInterval(LocalDate.now().minusDays(7), LocalDate.now());

        // Assert
        assertThat(rankings).extracting("movieId").containsExactly(MovieFixture.ALIEN_ID);
        assertThat(rankings).extracting("totalComments").containsExactly(1);
        assertThat(rankings).extracting("rank").containsExactly(1);
    }
}