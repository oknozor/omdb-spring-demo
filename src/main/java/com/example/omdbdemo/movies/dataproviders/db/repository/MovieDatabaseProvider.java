package com.example.omdbdemo.movies.dataproviders.db.repository;

import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.core.port.MovieProvider;
import com.example.omdbdemo.movies.dataproviders.db.entity.MovieEntity;
import com.example.omdbdemo.movies.dataproviders.db.entity.mapper.MovieEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class MovieDatabaseProvider implements MovieProvider {
    private final MovieEntityMapper mapper;
    private final MovieEntityRepository repository;

    @Override
    public Optional<Movie> byId(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Movie createOrUpdate(Movie movie) {
        MovieEntity movieUpdate = mapper.fromDomain(movie);
        MovieEntity updatedMovie = repository.save(movieUpdate);

        return mapper.toDomain(updatedMovie);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Movie> getAll() {
        List<MovieEntity> all = repository.findAll();
        return mapper.toDomain(all);
    }

    @Override
    public boolean exists(String id) {
        return repository.existsById(id);
    }
}
