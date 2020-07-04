package com.example.omdbdemo.comments.entrypoints.dto;

import com.example.omdbdemo.comments.core.model.Comment;
import com.example.omdbdemo.comments.entrypoints.dto.mapper.CommentDtoMapper;
import com.example.omdbdemo.config.annotation.MapperTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MapperTest
class CommentDtoMapperTest {
    @Autowired
    CommentDtoMapper mapper;

    @Test
    @DisplayName("Should map create comment command to domain")
    void mapCreateCommandToDomainOk() {
        // Arrange
        CreateCommentCommnand commnand = CreateCommentCommnand.builder()
                .body("Nice movie")
                .movieId("Sprited Away")
                .build();
        // Act
        Comment comment = mapper.toDomain(commnand);

        // Assert
        assertThat(comment.getBody()).isEqualTo("Nice movie");
        assertThat(comment.getMovieId()).isEqualTo("Sprited Away");
    }

    @Test
    @DisplayName("Should map model to dto")
    void mapDomainToDtoOk() {
        // Arrange
        Comment comment = Comment.builder()
                .body("Nice movie")
                .movieId("Sprited Away")
                .build();
        // Act
        CommentDto dto = mapper.fromDomain(comment);

        // Assert
        assertThat(dto.getBody()).isEqualTo("Nice movie");
        assertThat(dto.getMovieId()).isEqualTo("Sprited Away");
    }

    @Test
    @DisplayName("Should map models to dtos")
    void mapDtosToDomainOk() {
        // Arrange
        List<Comment> comment = List.of(Comment.builder()
                        .body("Nice movie")
                        .movieId("Sprited Away")
                        .build(),
                Comment.builder()
                        .body("It's Ok")
                        .movieId("L'arme fatale")
                        .build()
        );
        // Act
        List<CommentDto> dtos = mapper.fromDomain(comment);

        // Assert
        assertThat(dtos).extracting("body").containsExactlyInAnyOrder("Nice movie", "It's Ok");
        assertThat(dtos).extracting("movieId").containsExactlyInAnyOrder("Sprited Away", "L'arme fatale");
    }

}