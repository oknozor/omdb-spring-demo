package com.example.omdbdemo.movies.dataproviders.db.entity.mapper;

import com.example.omdbdemo.config.annotation.MapperTest;
import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.dataproviders.db.entity.MovieEntity;
import com.example.omdbdemo.movies.dataproviders.db.entity.MovieFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@MapperTest
class MovieEntityMapperTest {

    @Autowired
    MovieEntityMapper movieEntityMapper;

    @Test
    @DisplayName("Should map movie entity to domain")
    void entityToModelOk() {
        // Arrange
        MovieEntity entity = MovieFixture.getAlienEntity();

        // Act
        Movie model = movieEntityMapper.toDomain(entity);

        // Assert
        assertThat(entity.getId()).isEqualTo(model.getId());
        assertThat(entity.getTitle()).isEqualTo(model.getTitle());
        assertThat(entity.getActors()).isEqualTo(model.getActors());
        assertThat(entity.getAwards()).isEqualTo(model.getAwards());
        assertThat(entity.getCountry()).isEqualTo(model.getCountry());
        assertThat(entity.getDirector()).isEqualTo(model.getDirector());
        assertThat(entity.getGenre()).isEqualTo(model.getGenre());
        assertThat(entity.getPlot()).isEqualTo(model.getPlot());
        assertThat(entity.getRuntime()).isEqualTo(model.getRuntime());
        assertThat(entity.getRelease()).isEqualTo(model.getRelease());
        assertThat(entity.getLanguage()).isEqualTo(model.getLanguage());
    }

    @Test
    @DisplayName("Should map movie domain to entity")
    void modelToEntityOk() {
        // Arrange
        Movie model = MovieFixture.getAlien();

        // Act
        MovieEntity entity = movieEntityMapper.fromDomain(model);

        // Assert
        assertThat(model.getId()).isEqualTo(entity.getId());
        assertThat(model.getTitle()).isEqualTo(entity.getTitle());
        assertThat(model.getActors()).isEqualTo(entity.getActors());
        assertThat(model.getAwards()).isEqualTo(entity.getAwards());
        assertThat(model.getCountry()).isEqualTo(entity.getCountry());
        assertThat(model.getDirector()).isEqualTo(entity.getDirector());
        assertThat(model.getGenre()).isEqualTo(entity.getGenre());
        assertThat(model.getPlot()).isEqualTo(entity.getPlot());
        assertThat(model.getRuntime()).isEqualTo(entity.getRuntime());
        assertThat(model.getRelease()).isEqualTo(entity.getRelease());
        assertThat(model.getLanguage()).isEqualTo(entity.getLanguage());
    }
}