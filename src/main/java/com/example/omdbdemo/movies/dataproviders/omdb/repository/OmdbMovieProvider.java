package com.example.omdbdemo.movies.dataproviders.omdb.repository;

import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.core.port.MovieFetcher;
import com.example.omdbdemo.movies.dataproviders.omdb.entity.OmdbMovieEntity;
import com.example.omdbdemo.movies.dataproviders.omdb.entity.mapper.OmdbMovieEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class OmdbMovieProvider implements MovieFetcher {

    private final OmdbClient client;
    private final OmdbMovieEntityMapper mapper;

    // Todo : implement feign http error handling
    @Override
    public Optional<Movie> byTitle(String title) {
        // This is a quick fix, omdb does not interpret url escape such as "%20", correctly
        // We might need to implement a custom url encoder or change for another
        // movie api.
        String formattedTitle = title.replace(" ", "+");
        OmdbMovieEntity movieByTitle = client.getMovieByTitle(formattedTitle);
        Movie movie = mapper.toDomain(movieByTitle);
        return Optional.of(movie);
    }
}
