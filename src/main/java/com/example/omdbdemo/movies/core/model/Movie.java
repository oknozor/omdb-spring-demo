package com.example.omdbdemo.movies.core.model;

import com.example.omdbdemo.comments.core.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class Movie {
    private String id;
    private String title;
    private String year;
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
    private Set<Comment> comments;
}