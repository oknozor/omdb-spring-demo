package com.example.omdbdemo.movies.dataproviders.omdb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
@With
public class OmdbMovieEntity {
    @JsonProperty("imdbID")
    private String id;
    private String title;
    private String year;
    private String release;
    private String runtime;
    private String genre;
    private String director;
    private String writer;
    private String plot;
    private String language;
    private String country;
    // FIXME: extract those to lists
    private String awards;
    private String actors;
}
