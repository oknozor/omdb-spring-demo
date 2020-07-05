package com.example.omdbdemo.movies.core.usecase;

import com.example.omdbdemo.comments.core.model.Comment;
import com.example.omdbdemo.common.core.exception.NoSuchResourceException;
import com.example.omdbdemo.config.annotation.UseCaseUnitTest;
import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.core.model.MovieFixture;
import com.example.omdbdemo.movies.core.port.MovieProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@UseCaseUnitTest
class GetMovieCommentsTest {
    GetMovieComments getMovieComments;

    @MockBean
    MovieProvider movieProvider;

    @BeforeEach
    void setUp() {
        this.getMovieComments = new GetMovieComments(movieProvider);
    }

    @Test
    @DisplayName("Should get comments for a movie")
    void setGetMovieOk() throws NoSuchResourceException {
        // Arrange
        Movie alien = MovieFixture.getAlien();
        when(movieProvider.byId(any())).thenReturn(Optional.of(alien));

        // Act
        List<Comment> comments = getMovieComments.execute(alien.getId());

        // Assert
        verify(movieProvider).byId(eq(alien.getId()));
        assertThat(comments).hasSize(1);
        assertThat(comments).extracting("body").containsExactlyInAnyOrder("Cannot wait for the sequel");

    }


    @Test
    @DisplayName("Should thrown no such resource when movie is not present")
    void getMovieFails() {
        // Arrange
        String invalidId = "La moustache";
        when(movieProvider.byId(any())).thenReturn(Optional.empty());

        // Act + Assert
        assertThatThrownBy(() -> getMovieComments.execute(invalidId)).isInstanceOf(NoSuchResourceException.class);
    }
}