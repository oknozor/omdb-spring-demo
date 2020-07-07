package com.example.omdbdemo.comments.entrypoints.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCommentCommnand {
    @JsonProperty("movie_id")
    private String movieId;
    private String body;
}
