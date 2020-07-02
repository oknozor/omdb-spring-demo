package com.example.omdbdemo.movies.core.usecase;

import com.example.omdbdemo.common.core.exception.NoSuchResourceException;
import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.core.port.MovieProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UpdateMovie {
    private final MovieProvider movieProvider;

    public Movie execute(Movie movie) throws NoSuchResourceException {
        String movieId = movie.getId();
        movieProvider.byId(movieId).orElseThrow(() -> new NoSuchResourceException(Movie.class, movieId));
        return movieProvider.createOrUpdate(movie);
    }
}
