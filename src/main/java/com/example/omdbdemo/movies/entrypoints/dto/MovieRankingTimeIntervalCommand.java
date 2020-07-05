package com.example.omdbdemo.movies.entrypoints.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieRankingTimeIntervalCommand {
    private LocalDate from;
    private LocalDate to;
}
