package com.example.omdbdemo.comments.entrypoints;

import com.example.omdbdemo.comments.core.model.Comment;
import com.example.omdbdemo.comments.core.usecase.CreateComment;
import com.example.omdbdemo.comments.core.usecase.GetAllComments;
import com.example.omdbdemo.comments.entrypoints.dto.CommentDto;
import com.example.omdbdemo.comments.entrypoints.dto.CreateCommentCommnand;
import com.example.omdbdemo.comments.entrypoints.dto.mapper.CommentDtoMapper;
import com.example.omdbdemo.common.core.exception.NoSuchResourceException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("comments")
@AllArgsConstructor
public class CommentController {
    private final GetAllComments getAllComments;
    private final CreateComment createComment;
    private CommentDtoMapper mapper;


    @PostMapping(
            value = "/comments",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommentDto> createComment(
            @RequestBody CreateCommentCommnand command
    ) throws NoSuchResourceException {
        Comment commentToCreate = mapper.toDomain(command);
        Comment newComment = createComment.execute(commentToCreate);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.fromDomain(newComment));
    }

    @GetMapping(value = "/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CommentDto>> getAllComments() {
        List<Comment> allComments = getAllComments.execute();
        return ResponseEntity.ok(mapper.fromDomain(allComments));
    }
}
