package com.example.omdbdemo.movies.dataproviders.db.repository;

import com.example.omdbdemo.movies.dataproviders.db.entity.MovieRankingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovieRankingRepository extends JpaRepository<MovieRankingEntity, String> {
    @Query(value = "SELECT movie_id, count(comment.id) AS comment_count, dense_rank() OVER (ORDER BY count(comment.id) DESC ) rank " +
            "FROM movie," +
            "     comment " +
            "WHERE movie.id = comment.movie_id " +
            "AND comment.created > ?1 " +
            "AND comment.created < ?2 " +
            "GROUP BY movie_id; ",
            nativeQuery = true)
    List<MovieRankingEntity> getRankingsWithInterval(LocalDateTime from, LocalDateTime to);

    @Query(value = "SELECT movie_id, count(comment.id) AS comment_count, dense_rank() OVER (ORDER BY count(comment.id) DESC ) rank " +
            "FROM movie," +
            "     comment " +
            "WHERE movie.id = comment.movie_id " +
            "GROUP BY movie_id; ",
            nativeQuery = true)
    List<MovieRankingEntity> getRankings();
}
