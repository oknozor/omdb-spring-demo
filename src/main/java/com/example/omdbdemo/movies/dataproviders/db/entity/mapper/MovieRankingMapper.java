package com.example.omdbdemo.movies.dataproviders.db.entity.mapper;

import com.example.omdbdemo.movies.core.model.MovieRanking;
import com.example.omdbdemo.movies.dataproviders.db.entity.MovieRankingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovieRankingMapper {
    MovieRanking toDomain(MovieRankingEntity entity);
    List<MovieRanking> toDomain(List<MovieRankingEntity> entity);
}
