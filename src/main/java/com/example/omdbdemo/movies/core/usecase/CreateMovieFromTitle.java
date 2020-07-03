package com.example.omdbdemo.movies.core.usecase;

import com.example.omdbdemo.common.core.exception.NoSuchResourceException;
import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.core.port.MovieFetcher;
import com.example.omdbdemo.movies.core.port.MovieProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class CreateMovieFromTitle {
    private final MovieProvider movieProvider;
    private final MovieFetcher movieFetcher;

    public Movie execute(String title) throws NoSuchResourceException {
        return movieFetcher.byTitle(title).map(movieProvider::createOrUpdate)
                .orElseThrow(() -> new NoSuchResourceException(Movie.class, title));
    }
}
