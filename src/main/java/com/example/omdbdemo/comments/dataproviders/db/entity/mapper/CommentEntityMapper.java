package com.example.omdbdemo.comments.dataproviders.db.entity.mapper;


import com.example.omdbdemo.comments.core.model.Comment;
import com.example.omdbdemo.comments.dataproviders.db.entity.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentEntityMapper {
    Comment toDomain(CommentEntity entity);
    List<Comment> toDomain(List<CommentEntity> entities);
    CommentEntity fromDomain(Comment model, String movieId);
    List<CommentEntity> fromDomain(List<Comment> models);
}
