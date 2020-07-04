package com.example.omdbdemo.comments.dataproviders.db.repository;

import com.example.omdbdemo.comments.dataproviders.db.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentEntityRepository extends JpaRepository<CommentEntity, Long> {

}
