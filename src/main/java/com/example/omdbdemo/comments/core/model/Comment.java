package com.example.omdbdemo.comments.core.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@With
public class Comment {

    public Comment(String body, String movieId) {
        this.body = body;
        this.movieId = movieId;
    }

    private String body;
    private String movieId;
    private LocalDateTime created;
}
