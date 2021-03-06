package com.example.omdbdemo.comments.dataproviders.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
@Data
@With
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movie_id")
    private String movieId;

    private String body;

    @CreationTimestamp
    private LocalDateTime created;
}
