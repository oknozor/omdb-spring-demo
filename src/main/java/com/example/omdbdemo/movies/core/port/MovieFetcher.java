package com.example.omdbdemo.movies.core.port;

import com.example.omdbdemo.movies.core.model.Movie;

import java.util.Optional;

public interface MovieFetcher {
    Optional<Movie> byTitle(String title);
}
