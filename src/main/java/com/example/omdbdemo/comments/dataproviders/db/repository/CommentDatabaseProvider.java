package com.example.omdbdemo.comments.dataproviders.db.repository;

import com.example.omdbdemo.comments.core.model.Comment;
import com.example.omdbdemo.comments.core.port.CommentProvider;
import com.example.omdbdemo.comments.dataproviders.db.entity.CommentEntity;
import com.example.omdbdemo.comments.dataproviders.db.entity.mapper.CommentEntityMapper;
import com.example.omdbdemo.movies.dataproviders.db.entity.MovieEntity;
import com.example.omdbdemo.movies.dataproviders.db.repository.MovieEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CommentDatabaseProvider implements CommentProvider {

    private final CommentEntityRepository commentEntityRepository;
    private final MovieEntityRepository movieRepository;
    private final CommentEntityMapper mapper;

    @Override
    public Optional<Comment> create(Comment comment) {
        Optional<MovieEntity> movieEntity = movieRepository.findById(comment.getMovieId());
        if(movieEntity.isEmpty()) {
            return Optional.empty();
        }

        CommentEntity commentEntity = mapper.fromDomain(comment, movieEntity.get().getId());
        CommentEntity savedComment = commentEntityRepository.save(commentEntity);
        return Optional.of(mapper.toDomain(savedComment));
    }

    @Override
    public List<Comment> getAll() {
        return mapper.toDomain(commentEntityRepository.findAll());
    }
}
