package com.example.omdbdemo.movies.dataproviders.db.repository;

import com.example.omdbdemo.config.SharedPostgresqlContainer;
import com.example.omdbdemo.movies.dataproviders.db.entity.MovieEntity;
import com.example.omdbdemo.movies.dataproviders.db.entity.MovieFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DummyDbTest {
    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = SharedPostgresqlContainer.getInstance();

    @Autowired
    MovieEntityRepository movieEntityRepository;

    @Test
    void should_create_movie() {
        // Arrange
        MovieEntity alien = MovieFixture.getAlien();

        // Act
        MovieEntity savedMovie = movieEntityRepository.save(alien);

        // Assert
        assertThat(savedMovie).isEqualTo(alien);
    }
}