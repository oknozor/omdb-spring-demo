package com.example.omdbdemo.comments.core.port;

import com.example.omdbdemo.comments.core.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentProvider {
    Optional<Comment> create(Comment comment);
    List<Comment> getAll();
}
