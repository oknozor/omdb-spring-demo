package com.example.omdbdemo.movies.entrypoints.dto.mapper;

import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.entrypoints.dto.MovieDto;
import com.example.omdbdemo.movies.entrypoints.dto.UpdateMovieCommand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovieDtoMapper {
    Movie toDomain(MovieDto dto);
    List<Movie> toDomain(List<MovieDto> dtos);
    MovieDto fromDomain(Movie model);
    List<MovieDto> fromDomain(List<Movie> models);

    Movie toDomain(UpdateMovieCommand movieDtoUpdate, String id);
}
