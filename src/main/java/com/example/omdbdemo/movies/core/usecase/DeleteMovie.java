package com.example.omdbdemo.movies.core.usecase;

import com.example.omdbdemo.common.core.exception.NoSuchResourceException;
import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.core.port.MovieProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DeleteMovie {
    private final MovieProvider movieProvider;

    public void execute(String movieId) throws NoSuchResourceException {
        if(!movieProvider.exists(movieId)) {
            throw new NoSuchResourceException(Movie.class, movieId);
        }
        movieProvider.delete(movieId);
    }
}
