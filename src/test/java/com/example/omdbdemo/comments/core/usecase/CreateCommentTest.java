package com.example.omdbdemo.comments.core.usecase;

import com.example.omdbdemo.comments.core.model.Comment;
import com.example.omdbdemo.comments.core.port.CommentProvider;
import com.example.omdbdemo.common.core.exception.NoSuchResourceException;
import com.example.omdbdemo.config.annotation.UseCaseUnitTest;
import com.example.omdbdemo.movies.dataproviders.db.entity.MovieFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@UseCaseUnitTest
class CreateCommentTest {

    CreateComment createComment;

    @MockBean
    CommentProvider commentProvider;

    @BeforeEach
    void setUp() {
        createComment = new CreateComment(commentProvider);
    }

    @Test
    @DisplayName("Should create a comment")
    void createCommentOk() throws NoSuchResourceException {
        // Arrange
        String alienId = MovieFixture.ALIEN_ID;
        Comment comment = new Comment("Alien avec Gérard Depardieu, génial!", MovieFixture.ALIEN_ID);
        when(commentProvider.create(comment)).thenReturn(Optional.of(comment));

        // Act
        createComment.execute(comment);

        // Assert
        assertThat(comment.getBody()).isEqualTo("Alien avec Gérard Depardieu, génial!");
    }

    @Test
    @DisplayName("Should throw no such resource when movie is not found on comment creation")
    void createCommentFails() {
        // Arrange
        String invalidId = "9999";
        Comment comment = new Comment("99 francs mais avec des euros!", invalidId);
        when(commentProvider.create(comment)).thenReturn(Optional.empty());

        // Act + Assert
        assertThatThrownBy(() -> createComment.execute(comment)).isInstanceOf(NoSuchResourceException.class);

    }
}