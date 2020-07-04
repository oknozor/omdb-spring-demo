package com.example.omdbdemo.movies.entrypoints.dto;

import com.example.omdbdemo.comments.entrypoints.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
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
    private List<CommentDto> comments;
}
