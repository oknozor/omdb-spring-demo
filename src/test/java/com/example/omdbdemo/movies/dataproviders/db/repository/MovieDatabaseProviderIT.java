package com.example.omdbdemo.movies.dataproviders.db.repository;

import com.example.omdbdemo.common.core.exception.NoSuchResourceException;
import com.example.omdbdemo.config.MapperTest;
import com.example.omdbdemo.config.SharedPostgresqlContainer;
import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.dataproviders.db.entity.MovieFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Import({MovieDatabaseProvider.class})
@DataJpaTest
@MapperTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MovieDatabaseProviderIT {

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = SharedPostgresqlContainer.getInstance();

    @Autowired
    MovieDatabaseProvider movieDatabaseProvider;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Should create movie")
    void createOk() {
        // Arrange
        Movie mononokeHime = new Movie()
                .withId("123")
                .withTitle("Mononoke Hime")
                .withRelease("1994")
                .withLanguage("Japanese")
                .withRuntime("2H")
                .withWriter("Miyazaki")
                .withPlot("Prince Ashitaka on a journey to heal is cursed wound")
                .withGenre("Japanese Animation")
                .withCountry("Japan")
                .withDirector("Ayaho Miyazaki")
                .withActors("Brad Pit")
                .withAwards("Best animation movie all of time");

        // Act
        Movie createdMovie = movieDatabaseProvider.createOrUpdate(mononokeHime);

        // Assert
        assertThat(createdMovie.getId()).isEqualTo("123");
        assertThat(createdMovie.getTitle()).isEqualTo("Mononoke Hime");
        assertThat(createdMovie.getRelease()).isEqualTo("1994");
        assertThat(createdMovie.getLanguage()).isEqualTo("Japanese");
        assertThat(createdMovie.getRuntime()).isEqualTo("2H");
        assertThat(createdMovie.getWriter()).isEqualTo("Miyazaki");
        assertThat(createdMovie.getCountry()).isEqualTo("Japan");
        assertThat(createdMovie.getGenre()).isEqualTo("Japanese Animation");
        assertThat(createdMovie.getPlot()).isEqualTo("Prince Ashitaka on a journey to heal is cursed wound");
        assertThat(createdMovie.getDirector()).isEqualTo("Ayaho Miyazaki");
        assertThat(createdMovie.getActors()).isEqualTo("Brad Pit");
        assertThat(createdMovie.getAwards()).isEqualTo("Best animation movie all of time");
    }

    @Test
    @DisplayName("Should get movie by id")
    void byIdOk() {
        // Arrange
        String alienId = MovieFixture.ALIEN_ID;

        // Act
        Optional<Movie> alien = movieDatabaseProvider.byId(alienId);

        // Assert
        assertThat(alien).isPresent();
        assertThat(alien.get().getTitle()).isEqualTo("Alien");
    }

    @Test
    @DisplayName("Should not get movie when movie is not present")
    void byIdFails() {
        // Arrange
        String invalidId = "Alien 4";

        // Act
        Optional<Movie> alien = movieDatabaseProvider.byId(invalidId);

        // Assert
        assertThat(alien).isNotPresent();
    }


    @Test
    @DisplayName("Should update movie")
    void updateOk() {
        // Arrange
        Movie alienFrenchEdit = MovieFixture.getAlien()
                .withActors("Gérard Depardieu");
        // Act
        Movie alienUpdated = movieDatabaseProvider.createOrUpdate(alienFrenchEdit);

        // Assert
        assertThat(alienUpdated.getActors()).contains("Gérard Depardieu");
    }

    @Test
    @DisplayName("Should delete movie")
    void deleteOk() {
        // Arrange
        String alienId = MovieFixture.ALIEN_ID;

        // Act
        movieDatabaseProvider.delete(alienId);

        // Assert
        assertThat(movieDatabaseProvider.byId(alienId)).isNotPresent();
    }

    @Test
    @DisplayName("Alien the movie should exist")
    void existByIdOk() throws NoSuchResourceException {
        // Arrange
        String alienId = MovieFixture.ALIEN_ID;

        // Act
        boolean exist = movieDatabaseProvider.exists(alienId);

        // Assert
        assertThat(exist).isTrue();
    }

    @Test
    @DisplayName("'La moustache'  the movie should not exist")
    void existByIdFail() {
        // Arrange
        String invalidId = "La moustache";

        // Act
        boolean exist = movieDatabaseProvider.exists(invalidId);

        // Assert
        assertThat(exist).isFalse();
    }

    @Test
    @DisplayName("Should get all movies")
    void getAllOk() {
        // Act
        List<Movie> movies = movieDatabaseProvider.getAll();

        // Assert
        assertThat(movies).hasSize(1);
        assertThat(movies.stream().map(Movie::getTitle)).containsExactly("Alien");
    }
}