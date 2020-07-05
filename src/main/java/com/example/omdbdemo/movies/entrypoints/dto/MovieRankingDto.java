package com.example.omdbdemo.movies.entrypoints.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRankingDto {
    private String movieId;
    private Integer totalComments;
    private Integer rank;
}
