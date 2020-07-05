package com.example.omdbdemo.movies.core.usecase;


import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.core.port.MovieProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class GetAllMovie {
    private final MovieProvider movieProvider;

    public List<Movie> execute() {
        return movieProvider.getAll();
    }
}