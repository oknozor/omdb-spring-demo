package com.example.omdbdemo.movies.dataproviders.db.entity;

import com.example.omdbdemo.comments.dataproviders.db.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Data
@With
public class MovieEntity {
    @Id
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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "movie_id")
    List<CommentEntity> comments;
}
