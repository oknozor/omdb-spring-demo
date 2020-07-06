package com.example.omdbdemo.movies.entrypoints;

import com.example.omdbdemo.comments.entrypoints.dto.mapper.CommentDtoMapper;
import com.example.omdbdemo.common.core.exception.NoSuchResourceException;
import com.example.omdbdemo.common.core.exception.ResourceAlreadyExistsException;
import com.example.omdbdemo.config.annotation.RestTest;
import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.core.model.MovieFixture;
import com.example.omdbdemo.movies.core.usecase.*;
import com.example.omdbdemo.movies.entrypoints.dto.CreateMovieByTitleCommand;
import com.example.omdbdemo.movies.entrypoints.dto.MovieDto;
import com.example.omdbdemo.movies.entrypoints.dto.MovieRankingDto;
import com.example.omdbdemo.movies.entrypoints.dto.UpdateMovieCommand;
import com.example.omdbdemo.movies.entrypoints.dto.mapper.MovieDtoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
    MovieDtoMapper movieMapper;

    @Autowired
    CommentDtoMapper commentMapper;

    @MockBean
    CreateMovieFromTitle createMovieFromTitle;
    @MockBean
    GetMovie getMovie;
    @MockBean
    GetAllMovie getAllMovie;
    @MockBean
    UpdateMovie updateMovie;
    @MockBean
    DeleteMovie deleteMovie;
    @MockBean
    GetMovieComments getMovieComments;
    @MockBean
    GetMovieRankings getMovieRankings;

    ObjectMapper jsonMapper = new ObjectMapper();

    @BeforeEach
    public void setUp(
            WebApplicationContext webApplicationContext,
            RestDocumentationContextProvider restDocumentation
    ) {
        jsonMapper = jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

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
        MovieDto alienDto = movieMapper.toDto(alien);

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
    @DisplayName("Should get all movies")
    void getAllMovieOk() throws Exception {
        // Arrange
        List<Movie> allMovies = List.of(MovieFixture.getAlien(), MovieFixture.getPrincessMononoke());
        when(getAllMovie.execute()).thenReturn(allMovies);
        List<MovieDto> allMovieDtos = movieMapper.toDto(allMovies);

        // Act + Assert
        String jsonContent = jsonMapper.writeValueAsString(allMovieDtos);
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/movies"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonContent))
                .andDo(document("movie-read-all-example",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    @DisplayName("Getting a movie by its id should return 404 when it is not present")
    void getMovieByIdFail() throws Exception {
        // Arrange
        String invalidId = "The expendable";
        when(getMovie.execute(MovieFixture.ALIEN_ID)).thenThrow(new NoSuchResourceException(Movie.class, invalidId));

        // Act + Assert
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/movies/{id}", MovieFixture.ALIEN_ID))
                .andExpect(status().isNotFound());
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
        Movie alienUpdated = movieMapper.toDomain(alienWithGerardDepardieuUpdateCommand, MovieFixture.ALIEN_ID);
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
        Movie unknownMovie = movieMapper.toDomain(alienWithGerardDepardieu, invalidId)
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

    @Test
    @DisplayName("Should get a movie comments by its imdb id")
    void getMovieCommentById() throws Exception {
        // Arrange
        Movie alien = MovieFixture.getAlien();
        when(getMovieComments.execute(MovieFixture.ALIEN_ID)).thenReturn(alien.getComments());
        MovieDto alienDto = movieMapper.toDto(alien);

        // Act + Assert
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/movies/{id}/comments", MovieFixture.ALIEN_ID))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonMapper.writeValueAsString(alienDto.getComments())))
                .andDo(document("movie-comments-read-example",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(parameterWithName("id").description("The imdb id of the movie to retrieve"))));
    }

    @Test
    @DisplayName("Getting movie comments by its id should return 404 when it is not present")
    void getMovieCommentByIdFail() throws Exception {
        // Arrange
        String invalidId = "The expendable";
        when(getMovieComments.execute(MovieFixture.ALIEN_ID)).thenThrow(new NoSuchResourceException(Movie.class, invalidId));

        // Act + Assert
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/movies/{id}/comments", MovieFixture.ALIEN_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should get movie rankings")
    void getMovieRankingsOk() throws Exception {
        // Arrange
        when(getMovieRankings.execute(null, null)).thenReturn(MovieFixture.rankings());

        // Act + Assert
        List<MovieRankingDto> expectedDtos = movieMapper.toRankingDto(MovieFixture.rankings());
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/movies/top"))
                .andExpect(content().json(jsonMapper.writeValueAsString(expectedDtos)))
                .andExpect(status().isOk())
                .andDo(document("movie-ranking-example",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    @DisplayName("Should get movie rankings between dates")
    void setGetMovieRankingsIntervalOk() throws Exception {
        // Arrange
        LocalDate from = LocalDate.now().minusWeeks(1);
        LocalDate to = LocalDate.now();
        when(getMovieRankings.execute(from, to)).thenReturn(MovieFixture.rankings());

        // Act + Assert
        String jsonContent = jsonMapper.writeValueAsString(movieMapper.toRankingDto(MovieFixture.rankings()));

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/movies/top")
                .param("from", from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .param("to", to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
                .andExpect(content().json(jsonContent))
                .andExpect(status().isOk())
                .andDo(document("movie-ranking-interval-example",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }


}