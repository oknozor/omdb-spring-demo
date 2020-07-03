package com.example.omdbdemo.movies.dataproviders.omdb.repository;

import com.example.omdbdemo.config.MapperTest;
import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.entrypoints.MovieController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OmdbMovieProvider.class)
@MapperTest
@EnableSpringDataWebSupport
@Import(OmdbClient.class)
class OmdbMovieProviderIntegrationTest {

    @Autowired
    OmdbMovieProvider omdbMovieProvider;

    @Test
    @DisplayName("Should get a movie by its title")
    public void getByTitleOk() {
        // Arrange
        String movieTitle = "Princess Mononoke";

        // Act
        Optional<Movie> princessMononoke = omdbMovieProvider.byTitle(movieTitle);

        // Assert
        assertThat(princessMononoke).isPresent();
        assertThat(princessMononoke.get().getId()).isEqualTo("tt0119698");
        assertThat(princessMononoke.get().getDirector()).isEqualTo("Hayao Miyazaki");
        assertThat(princessMononoke.get().getTitle()).isEqualTo("Princess Mononoke");
        assertThat(princessMononoke.get().getCountry()).isEqualTo("Japan");
        assertThat(princessMononoke.get().getAwards()).isEqualTo("13 wins & 6 nominations.");
        assertThat(princessMononoke.get().getActors()).isEqualTo("Billy Crudup, Billy Bob Thornton, Minnie Driver, John DiMaggio");
        assertThat(princessMononoke.get().getPlot()).isEqualTo("On a journey to find the cure for a Tatarigami's curse, Ashitaka finds himself in the middle of a war between the forest gods and Tatara, a mining colony. In this quest he also meets San, the Mononoke Hime.");
        assertThat(princessMononoke.get().getGenre()).isEqualTo("Animation, Adventure, Fantasy");
        assertThat(princessMononoke.get().getWriter()).isEqualTo("Hayao Miyazaki, Neil Gaiman (adapted by: English version)");
        assertThat(princessMononoke.get().getLanguage()).isEqualTo("Japanese");
        assertThat(princessMononoke.get().getRuntime()).isEqualTo("134 min");
        assertThat(princessMononoke.get().getYear()).isEqualTo("1997");
    }

    @Test
    @DisplayName("Should fail if movie is not found")
    public void byTitleFails() {
        // Arrange
        String movieTitle = "azeraetjegle";

        // Act
        Optional<Movie> movie = omdbMovieProvider.byTitle(movieTitle);

        // Assert
        assertThat(movie).isNotPresent();

    }
}