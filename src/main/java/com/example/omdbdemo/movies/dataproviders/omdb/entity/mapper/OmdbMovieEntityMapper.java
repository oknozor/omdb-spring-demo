package com.example.omdbdemo.movies.dataproviders.omdb.entity.mapper;

import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.dataproviders.omdb.entity.OmdbMovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OmdbMovieEntityMapper {
    Movie toDomain(OmdbMovieEntity entity);
    List<Movie> toDomain(List<OmdbMovieEntity> entities);
}
