package com.example.omdbdemo.movies.entrypoints.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRankingDto {
    @JsonProperty("movie_id")
    private String movieId;
    @JsonProperty("total_comments")
    private Integer totalComments;
    private Integer rank;
}
