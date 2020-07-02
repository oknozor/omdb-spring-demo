package com.example.omdbdemo.movies.dataproviders.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class RatingId implements Serializable  {
    @Column(name = "movie_id")
    private Long movieId;
    private String source;
}
