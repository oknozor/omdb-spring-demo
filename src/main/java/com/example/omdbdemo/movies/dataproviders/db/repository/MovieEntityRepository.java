package com.example.omdbdemo.movies.dataproviders.db.repository;

import com.example.omdbdemo.movies.dataproviders.db.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieEntityRepository extends JpaRepository<MovieEntity, String> {
}
