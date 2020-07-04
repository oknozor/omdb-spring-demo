package com.example.omdbdemo.comments.entrypoints.dto.mapper;

import com.example.omdbdemo.comments.core.model.Comment;
import com.example.omdbdemo.comments.entrypoints.dto.CommentDto;
import com.example.omdbdemo.comments.entrypoints.dto.CreateCommentCommnand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentDtoMapper {
    Comment toDomain(CreateCommentCommnand dto);
    CommentDto fromDomain(Comment model);
    List<CommentDto> fromDomain(List<Comment> model);
}
