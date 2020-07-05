package com.example.omdbdemo.movies.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieRanking {
    private String movieId;
    private Integer totalComments;
    private Integer rank;
}
