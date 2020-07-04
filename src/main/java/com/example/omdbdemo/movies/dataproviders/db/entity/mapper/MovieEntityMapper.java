package com.example.omdbdemo.movies.dataproviders.db.entity.mapper;

import com.example.omdbdemo.comments.dataproviders.db.entity.mapper.CommentEntityMapper;
import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.dataproviders.db.entity.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = CommentEntityMapper.class)
public interface MovieEntityMapper {
    Movie toDomain(MovieEntity entity);
    List<Movie> toDomain(List<MovieEntity> entities);
    MovieEntity fromDomain(Movie model);
    List<MovieEntity> fromDomain(List<Movie> models);
}

