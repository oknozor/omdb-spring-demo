package com.example.omdbdemo.movies.core.port;

import com.example.omdbdemo.movies.core.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieProvider {

    Optional<Movie> byId(String id);

    Movie createOrUpdate(Movie movie);

    void delete(String id);

    List<Movie> getAll();

    boolean exists(String id);
}
