package com.example.omdbdemo.movies.entrypoints;

import com.example.omdbdemo.comments.core.model.Comment;
import com.example.omdbdemo.comments.entrypoints.dto.CommentDto;
import com.example.omdbdemo.comments.entrypoints.dto.mapper.CommentDtoMapper;
import com.example.omdbdemo.common.core.exception.NoSuchResourceException;
import com.example.omdbdemo.common.core.exception.ResourceAlreadyExistsException;
import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.core.model.MovieRanking;
import com.example.omdbdemo.movies.core.usecase.*;
import com.example.omdbdemo.movies.entrypoints.dto.CreateMovieByTitleCommand;
import com.example.omdbdemo.movies.entrypoints.dto.MovieDto;
import com.example.omdbdemo.movies.entrypoints.dto.MovieRankingDto;
import com.example.omdbdemo.movies.entrypoints.dto.UpdateMovieCommand;
import com.example.omdbdemo.movies.entrypoints.dto.mapper.MovieDtoMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController("/movies")
@AllArgsConstructor
public class MovieController {
    private final CreateMovieFromTitle createMovieFromTitle;
    private final GetMovie getMovie;
    private final UpdateMovie updateMovie;
    private final DeleteMovie deleteMovie;
    private final GetMovieComments getMovieComment;
    private final GetMovieRankings getMovieRankings;

    private final MovieDtoMapper movieMapper;
    private final CommentDtoMapper commentMapper;

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @PostMapping(
            value = "/movies",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<MovieDto> createMovie(
            @RequestBody CreateMovieByTitleCommand command
    ) throws NoSuchResourceException, ResourceAlreadyExistsException {
        String title = command.getTitle();
        Movie createdMovie = createMovieFromTitle.execute(title);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(movieMapper.toDto(createdMovie));
    }

    @GetMapping(value = "/movies/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDto> getMovieById(@PathVariable String id) throws NoSuchResourceException {
        Movie movie = getMovie.execute(id);
        return ResponseEntity.ok(movieMapper.toDto(movie));
    }

    @GetMapping(value = "/movies/{id}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CommentDto>> getCommentByMovieId(
            @PathVariable String id
    ) throws NoSuchResourceException {
        List<Comment> comments = getMovieComment.execute(id);
        return ResponseEntity.ok(commentMapper.fromDomain(comments));
    }

    @PutMapping(
            value = "/movies/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<MovieDto> updateMovie(@RequestBody UpdateMovieCommand movieDtoUpdate, @PathVariable String id)
            throws NoSuchResourceException {
        Movie updateCommand = movieMapper.toDomain(movieDtoUpdate, id);
        Movie updatedMovie = updateMovie.execute(updateCommand);
        MovieDto updatedMovieDto = movieMapper.toDto(updatedMovie);

        return ResponseEntity.ok(updatedMovieDto);
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String id) throws NoSuchResourceException {
        deleteMovie.execute(id);

        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MovieDto> getAllMovies() {
        logger.warn("get all movie not implemented");
        return List.of();
    }

    @GetMapping(value = "/movies/top", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MovieRankingDto>> getMovieRankings(
            @RequestParam(required = false) LocalDate from, @RequestParam(required = false) LocalDate to
    ) {
        List<MovieRanking> rankings = getMovieRankings.execute(from, to);
        return ResponseEntity.ok(movieMapper.toRankingDto(rankings));
    }
}
