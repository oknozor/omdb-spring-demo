package com.example.omdbdemo.movies.dataproviders.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Data
@With
public class    MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String year;
    private String rated;
    private String release;
    private String runtime;
    private String genre;
    private String director;
    private String writer;
    private String actors;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String poster;
    private String metascore;
    private String imdbRating;
    private String imdbVotes;
    private String imdbId;
    private String type;
    private String dvd;
    private String boxOffice;
    private String production;
    private String website;
    private String response;

    @OneToMany
    @JoinColumn(name = "movie_id")
    private Set<RatingEntity> ratings;
}
