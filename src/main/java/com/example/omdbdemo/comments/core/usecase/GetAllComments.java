package com.example.omdbdemo.comments.core.usecase;

import com.example.omdbdemo.comments.core.model.Comment;
import com.example.omdbdemo.comments.core.port.CommentProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GetAllComments {

    private final CommentProvider commentProvider;

    public List<Comment> execute() {
        return commentProvider.getAll();
    }
}
