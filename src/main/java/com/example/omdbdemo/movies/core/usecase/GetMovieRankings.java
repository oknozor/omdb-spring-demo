package com.example.omdbdemo.movies.core.usecase;

import com.example.omdbdemo.movies.core.model.MovieRanking;
import com.example.omdbdemo.movies.core.port.MovieProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@AllArgsConstructor
public class GetMovieRankings {
    private final MovieProvider movieProvider;

    public List<MovieRanking> execute(LocalDate from, LocalDate to) {
        return movieProvider.getRankingsWithInterval(from, to);

    }
}
