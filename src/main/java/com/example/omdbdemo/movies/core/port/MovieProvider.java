package com.example.omdbdemo.movies.core.port;

import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.core.model.MovieRanking;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieProvider {

    Optional<Movie> byId(String id);

    Movie createOrUpdate(Movie movie);

    void delete(String id);

    List<Movie> getAll();

    List<MovieRanking> getRankings();

    List<MovieRanking> getRankingsWithInterval(LocalDate from, LocalDate to);

    boolean exists(String id);

    boolean existByTitle(String title);
}
