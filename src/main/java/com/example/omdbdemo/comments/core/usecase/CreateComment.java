package com.example.omdbdemo.comments.core.usecase;

import com.example.omdbdemo.comments.core.model.Comment;
import com.example.omdbdemo.comments.core.port.CommentProvider;
import com.example.omdbdemo.common.core.exception.NoSuchResourceException;
import com.example.omdbdemo.movies.core.model.Movie;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateComment {

    private final CommentProvider commentProvider;

    public Comment execute(Comment comment) throws NoSuchResourceException {
        return commentProvider.create(comment).orElseThrow(() -> new NoSuchResourceException(Movie.class, comment.getMovieId()));
    }
}