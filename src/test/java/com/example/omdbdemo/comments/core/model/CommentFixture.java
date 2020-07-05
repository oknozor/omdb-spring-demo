package com.example.omdbdemo.comments.core.model;

import com.example.omdbdemo.comments.dataproviders.db.entity.CommentEntity;
import com.example.omdbdemo.movies.core.model.MovieFixture;

import java.time.LocalDateTime;
import java.util.List;

public class CommentFixture {
    public static List<Comment> getAllComments() {
        return List.of(getCommentForMononoke(), getCommentForAlien());
    }

    public static List<CommentEntity> getAllCommentEntities() {
        return List.of(getCommentEntityForMononoke(), getCommentEntityForAlien());
    }

    public static Comment getCommentForMononoke() {
        return new Comment("Best movie ever", MovieFixture.MONONOKE_ID, LocalDateTime.now().minusYears(1));
    }

    public static Comment getCommentWithMovieId(String movieId) {
        return new Comment("Best movie ever", movieId, LocalDateTime.now().minusYears(1));
    }


    public static Comment getCommentForAlien() {
        return new Comment("Cannot wait for the sequel", MovieFixture.ALIEN_ID, LocalDateTime.now().minusYears(1));
    }

    public static CommentEntity getCommentEntityForAlien() {
        return new CommentEntity(1L, MovieFixture.ALIEN_ID, "Cannot wait for the sequel", LocalDateTime.now().minusYears(1));
    }

    public static CommentEntity getCommentEntityForMononoke() {
        return new CommentEntity(2L, MovieFixture.MONONOKE_ID, "Best movie ever", LocalDateTime.now().minusYears(1));
    }
}