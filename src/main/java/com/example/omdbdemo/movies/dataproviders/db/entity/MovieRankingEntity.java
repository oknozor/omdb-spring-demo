package com.example.omdbdemo.movies.dataproviders.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "movie_rank")
public class MovieRankingEntity {
    @Id
    @Column(name = "movie_id")
    private String movieId;
    @Column(name = "comment_count")
    private Integer totalComments;
    private Integer rank;
}
