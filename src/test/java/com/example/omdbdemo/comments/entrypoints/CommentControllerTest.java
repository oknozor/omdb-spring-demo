package com.example.omdbdemo.comments.entrypoints;

import com.example.omdbdemo.comments.core.model.Comment;
import com.example.omdbdemo.comments.core.model.CommentFixture;
import com.example.omdbdemo.comments.core.usecase.CreateComment;
import com.example.omdbdemo.comments.core.usecase.GetAllComments;
import com.example.omdbdemo.comments.entrypoints.dto.CreateCommentCommnand;
import com.example.omdbdemo.comments.entrypoints.dto.mapper.CommentDtoMapper;
import com.example.omdbdemo.common.core.exception.NoSuchResourceException;
import com.example.omdbdemo.config.annotation.RestTest;
import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.core.model.MovieFixture;
import com.example.omdbdemo.movies.entrypoints.dto.mapper.MovieDtoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
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

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RestTest
@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MovieDtoMapper mapper;

    @MockBean
    GetAllComments getAllComments;
    @MockBean
    CreateComment createComment;

    @Autowired
    CommentDtoMapper commentMapper;

    ObjectMapper jsonMapper = new ObjectMapper();

    @BeforeEach
    public void setUp(
            WebApplicationContext webApplicationContext,
            RestDocumentationContextProvider restDocumentation
    ) {
        jsonMapper = jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        jsonMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation).uris())
                .alwaysDo(document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .build();
    }

    @Test
    @DisplayName("Should get all comment")
    void getAllCommentOk() throws Exception {
        // Arrange
        List<Comment> allComments = CommentFixture.getAllComments();
        when(getAllComments.execute()).thenReturn(allComments);

        // Act + Assert
        String jsonContent = jsonMapper.writeValueAsString(commentMapper.fromDomain(allComments));
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/comments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonContent))
                .andDo(document("comment-read-all-example",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    @DisplayName("Should create a comment")
    void createCommentOk() throws Exception {
        // Arrange
        Comment newComment = Comment.builder()
                .body("Better that Mars attack!")
                .movieId(MovieFixture.ALIEN_ID)
                .build();

        when(createComment.execute(newComment)).thenReturn(newComment);

        // Act + Assert
        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/comments")
                .content(jsonMapper.writeValueAsString(new CreateCommentCommnand(MovieFixture.ALIEN_ID, "Better that Mars attack!")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(document("comment-create-example",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                attributes(key("title").value("Fields for comment creation")),
                                fieldWithPath("movie_id").description("Id of the movie to comment"),
                                fieldWithPath("body").description("Comment content")
                        )));
    }

    @Test
    @DisplayName("Should return 404 when movie is not present")
    void createMovieOk() throws Exception {
        // Arrange
        Comment newComment = Comment.builder()
                .body("Better that Mars attack!")
                .movieId("Lord of the rings")
                .build();

        when(createComment.execute(newComment)).thenThrow(new NoSuchResourceException(Movie.class, "Lord of the rings"));

        // Act + Assert
        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/comments")
                .content(jsonMapper.writeValueAsString(new CreateCommentCommnand("Lord of the rings", "Better that Mars attack!")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}