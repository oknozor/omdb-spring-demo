package com.example.omdbdemo.movies.dataproviders.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.*;

@Entity
@Table(name = "movie_rating")
@NoArgsConstructor
@AllArgsConstructor
@Data
@With
public class RatingEntity {
    @EmbeddedId
    private RatingId id;
    private String value;
}
