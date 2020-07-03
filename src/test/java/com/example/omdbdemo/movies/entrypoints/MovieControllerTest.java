package com.example.omdbdemo.movies.entrypoints;

import com.example.omdbdemo.config.MapperTest;
import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.core.usecase.CreateMovieFromTitle;
import com.example.omdbdemo.movies.core.usecase.DeleteMovie;
import com.example.omdbdemo.movies.core.usecase.GetMovie;
import com.example.omdbdemo.movies.core.usecase.UpdateMovie;
import com.example.omdbdemo.movies.dataproviders.db.entity.MovieFixture;
import com.example.omdbdemo.movies.entrypoints.dto.MovieDto;
import com.example.omdbdemo.movies.entrypoints.dto.mapper.MovieDtoMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MovieController.class)
@MapperTest
@EnableSpringDataWebSupport
class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MovieDtoMapper mapper;

    @MockBean
    CreateMovieFromTitle createMovieFromTitle;
    @MockBean
    GetMovie getMovie;
    @MockBean
    DeleteMovie deleteMovie;
    @MockBean
    UpdateMovie updateMovie;

    ObjectMapper jsonMapper = new ObjectMapper();


    @Test
    @DisplayName("Should get a movie by its imdb id")
    public void getMovieById() throws Exception {
        Movie alien = MovieFixture.getAlien();
        when(getMovie.execute(MovieFixture.ALIEN_ID)).thenReturn(alien);
        MovieDto alienDto = mapper.fromDomain(alien);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/movies/" + MovieFixture.ALIEN_ID))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonMapper.writeValueAsString(alienDto)));
    }
}