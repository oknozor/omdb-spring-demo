package com.example.omdbdemo.comments.dataproviders.db.repository;

import com.example.omdbdemo.comments.core.model.Comment;
import com.example.omdbdemo.config.SharedPostgresqlContainer;
import com.example.omdbdemo.config.annotation.DatabaseTest;
import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.dataproviders.db.entity.MovieFixture;
import com.example.omdbdemo.movies.dataproviders.db.repository.MovieDatabaseProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DatabaseTest
@Import(CommentDatabaseProvider.class)
class CommentDatabaseProviderTest {

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = SharedPostgresqlContainer.getInstance();

    @Autowired
    CommentDatabaseProvider commentDatabaseProvider;

    @Autowired
    MovieDatabaseProvider movieDatabaseProvider;

    @Test@DisplayName("Should create comment")
    void createOk() {
        // Arrange
        Comment commentOnAlien = Comment.builder()
                .body("Better than Prometheus!")
                .movieId(MovieFixture.ALIEN_ID)
                .build();

        // Act
        Optional<Comment> commentSaved = commentDatabaseProvider.create(commentOnAlien);
        Optional<Movie> movie = movieDatabaseProvider.byId(MovieFixture.ALIEN_ID);

        // Assert
        assertThat(commentSaved).isPresent();
        assertThat(movie.get().getComments()).extracting("body").contains("Better than Prometheus!");
        assertThat(commentSaved.get().getBody()).isEqualTo("Better than Prometheus!");
        assertThat(commentSaved.get().getMovieId()).isEqualTo(MovieFixture.ALIEN_ID);
    }

    @Test
    @DisplayName("Should create comment, fail when movie is not present")
    void createFail() {
        // Arrange
        String invalidId = "Lord of the rings";
        Comment commentOnAlien = Comment.builder()
                .body("Better than The Hobbit!")
                .movieId(invalidId)
                .build();

        // Act + Assert
        Optional<Comment> commentSaved = commentDatabaseProvider.create(commentOnAlien);

        // Assert
        assertThat(commentSaved).isNotPresent();
    }
    @Test
    @DisplayName("Should get all comments")
    void getAll() {
        // Act
        List<Comment> allComments = commentDatabaseProvider.getAll();

        // Assert
        assertThat(allComments).extracting("body").contains("Cannot wait for the sequel");
        assertThat(allComments).extracting("movieId").contains(MovieFixture.ALIEN_ID);
    }
}