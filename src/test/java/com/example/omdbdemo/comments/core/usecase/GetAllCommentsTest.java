package com.example.omdbdemo.comments.core.usecase;

import com.example.omdbdemo.comments.core.model.Comment;
import com.example.omdbdemo.comments.core.model.CommentFixture;
import com.example.omdbdemo.comments.core.port.CommentProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class GetAllCommentsTest {

    GetAllComments getAllComments;

    @MockBean
    CommentProvider commentProvider;

    @BeforeEach
    void setUp() {
        getAllComments = new GetAllComments(commentProvider);
    }

    @Test
    @DisplayName("Should get all comments")
    void getAllCommentsOk() {
        // Arrange
        when(commentProvider.getAll()).thenReturn(CommentFixture.getAllComments());

        // Act
        List<Comment> allComments = getAllComments.execute();

        // Assert
        assertThat(allComments).extracting("body")
                .containsExactlyInAnyOrder("Best movie ever", "Cannot wait for the sequel");
    }
}