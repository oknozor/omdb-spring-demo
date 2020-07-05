package com.example.omdbdemo.comments.dataproviders.db.entity.mapper;

import com.example.omdbdemo.comments.core.model.Comment;
import com.example.omdbdemo.comments.core.model.CommentFixture;
import com.example.omdbdemo.comments.dataproviders.db.entity.CommentEntity;
import com.example.omdbdemo.config.annotation.MapperTest;
import com.example.omdbdemo.movies.core.model.MovieFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@MapperTest
class CommentEntityMapperTest {

    @Autowired
    CommentEntityMapper mapper;

    @Test
    @DisplayName("Should map model to entity")
    void domainToEntityOk() {
        // Arrange
        Comment comment = CommentFixture.getCommentForAlien();

        // Act
        CommentEntity commentEntity = mapper.fromDomain(comment, MovieFixture.ALIEN_ID);

        // Assert
        assertThat(commentEntity.getBody()).isEqualTo(comment.getBody());
    }

    @Test
    @DisplayName("Should map entity to model")
    void entityToDomainOk() {
        // Arrange
        CommentEntity commentEntity = CommentFixture.getCommentEntityForAlien();

        // Act
        Comment comment = mapper.toDomain(commentEntity);

        // Assert
        assertThat(comment.getBody()).isEqualTo(comment.getBody());
        assertThat(comment.getMovieId()).isEqualTo(MovieFixture.ALIEN_ID);
    }
}