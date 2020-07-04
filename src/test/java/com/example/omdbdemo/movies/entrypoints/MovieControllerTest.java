package com.example.omdbdemo.movies.entrypoints;

import com.example.omdbdemo.common.core.exception.NoSuchResourceException;
import com.example.omdbdemo.common.core.exception.ResourceAlreadyExistsException;
import com.example.omdbdemo.config.annotation.RestTest;
import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.core.usecase.CreateMovieFromTitle;
import com.example.omdbdemo.movies.core.usecase.DeleteMovie;
import com.example.omdbdemo.movies.core.usecase.GetMovie;
import com.example.omdbdemo.movies.core.usecase.UpdateMovie;
import com.example.omdbdemo.movies.dataproviders.db.entity.MovieFixture;
import com.example.omdbdemo.movies.entrypoints.dto.CreateMovieByTitleCommand;
import com.example.omdbdemo.movies.entrypoints.dto.MovieDto;
import com.example.omdbdemo.movies.entrypoints.dto.UpdateMovieCommand;
import com.example.omdbdemo.movies.entrypoints.dto.mapper.MovieDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RestTest
@WebMvcTest(MovieController.class)
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

    @BeforeEach
    public void setUp(
            WebApplicationContext webApplicationContext,
            RestDocumentationContextProvider restDocumentation
    ) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .build();
    }

    @Test
    @DisplayName("Should get a movie by its imdb id")
    void getMovieById() throws Exception {
        // Arrange
        Movie alien = MovieFixture.getAlien();
        when(getMovie.execute(MovieFixture.ALIEN_ID)).thenReturn(alien);
        MovieDto alienDto = mapper.fromDomain(alien);

        // Act + Assert
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/movies/{id}", MovieFixture.ALIEN_ID))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonMapper.writeValueAsString(alienDto)))
                .andDo(document("movie-read-example",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                pathParameters(parameterWithName("id").description("The imdb id of the movie to retrieve"))));
    }

    @Test
    @DisplayName("Should delete a movie by its imdb id")
    void deleteMovie() throws Exception {
        // Arrange
        // Act + Assert
        this.mockMvc.perform(RestDocumentationRequestBuilders.delete("/movies/{id}", MovieFixture.ALIEN_ID))
                .andExpect(status().isNoContent())
                .andDo(document("movie-delete-example",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(parameterWithName("id").description("The imdb id of the movie to delete"))));
    }

    @Test
    @DisplayName("Should update a movie by its imdb id")
    void updateMovie() throws Exception {
        // Arrange
        UpdateMovieCommand alienWithGerardDepardieuUpdateCommand = MovieFixture.updateAlienWithGerardDePardieu();
        Movie alienUpdated = mapper.toDomain(alienWithGerardDepardieuUpdateCommand, MovieFixture.ALIEN_ID);
        when(updateMovie.execute(alienUpdated)).thenReturn(alienUpdated);

        // Act + Assert
        this.mockMvc.perform(RestDocumentationRequestBuilders.put("/movies/{id}", MovieFixture.ALIEN_ID)
                .content(jsonMapper.writeValueAsString(alienWithGerardDepardieuUpdateCommand))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("movie-update-example",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                attributes(key("title").value("Fields for movie update")),
                                fieldWithPath("title").description("The movie title"),
                                fieldWithPath("year").description("The movie release year"),
                                fieldWithPath("release").description("The movie release date"),
                                fieldWithPath("runtime").description("Duration of the movie"),
                                fieldWithPath("genre").description("The movie genre"),
                                fieldWithPath("director").description("The movie director"),
                                fieldWithPath("writer").description("A comma separated list of writer"),
                                fieldWithPath("actors").description("A comma separated list of actors"),
                                fieldWithPath("plot").description("The movie plot"),
                                fieldWithPath("language").description("The movie language"),
                                fieldWithPath("country").description("The movie country"),
                                fieldWithPath("awards").description("A comma separated list of awards")
                        ),
                        pathParameters(parameterWithName("id").description("The imdb id of the movie to update"))));
    }

    @Test
    @DisplayName("Should return 404 on update when movie is not found")
    void updateMovie404() throws Exception {
        // Arrange
        String invalidId = "Blanche neige";
        UpdateMovieCommand alienWithGerardDepardieu = MovieFixture.updateAlienWithGerardDePardieu();
        Movie unknownMovie = mapper.toDomain(alienWithGerardDepardieu, invalidId)
                .withId(invalidId);
        when(updateMovie.execute(unknownMovie)).thenThrow(new NoSuchResourceException(Movie.class, unknownMovie.getId()));

        // Act + Assert
        this.mockMvc.perform(RestDocumentationRequestBuilders.put("/movies/{id}", invalidId)
                .content(jsonMapper.writeValueAsString(alienWithGerardDepardieu))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should create a movie given a valid title")
    void createMovieOk() throws Exception {
        // Arrange
        String princessMononokeTitle = "Princess Mononoke";
        when(createMovieFromTitle.execute(princessMononokeTitle)).thenReturn(MovieFixture.getPrincessMononoke());

        // Act + Assert
        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/movies")
                .content(jsonMapper.writeValueAsString(new CreateMovieByTitleCommand(princessMononokeTitle)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(document("movie-create-example",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                attributes(key("title").value("Fields for movie creation")),
                                fieldWithPath("title").description("Title of the movie to create")
                        )));
    }

    @Test
    @DisplayName("Should return 404 when creating a movie with infalid title")
    void createMovieNotFound() throws Exception {
        // Arrange
        String invalidTitle = "Hohohohoho";
        when(createMovieFromTitle.execute(eq(invalidTitle))).thenThrow(new NoSuchResourceException(Movie.class, invalidTitle));

        // Act + Assert
        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/movies")
                .content(jsonMapper.writeValueAsString(new CreateMovieByTitleCommand(invalidTitle)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should return 409 when creating a movie that exist in database already")
    void createMovieConflict() throws Exception {
        // Arrange
        String alreadyExistingMovie = MovieFixture.ALIEN_ID;
        when(createMovieFromTitle.execute(alreadyExistingMovie)).thenThrow(new ResourceAlreadyExistsException(Movie.class, alreadyExistingMovie));

        // Act + Assert
        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/movies")
                .content(jsonMapper.writeValueAsString(new CreateMovieByTitleCommand(alreadyExistingMovie)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

}