package com.example.omdbdemo.movies.core.usecase;

import com.example.omdbdemo.comments.core.model.Comment;
import com.example.omdbdemo.common.core.exception.NoSuchResourceException;
import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.core.port.MovieProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class GetMovieComments {
    private final MovieProvider movieProvider;

    public List<Comment> execute(String movieId) throws NoSuchResourceException {
        Movie movie = movieProvider.byId(movieId).orElseThrow(() -> new NoSuchResourceException(Movie.class, movieId));
        return movie.getComments();
    }
}
