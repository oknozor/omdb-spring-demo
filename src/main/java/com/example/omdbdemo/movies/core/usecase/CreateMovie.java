package com.example.omdbdemo.movies.core.usecase;

import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.core.port.MovieProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class CreateMovie {
    private final MovieProvider movieProvider;

    public Movie execute(Movie movie) {
        return movieProvider.createOrUpdate(movie);
    }
}
