package com.example.omdbdemo.comments.entrypoints.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentCommnand {
    private String movieId;
    private String body;
}
