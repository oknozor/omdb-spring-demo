package com.example.omdbdemo.movies.entrypoints;

import com.example.omdbdemo.common.core.exception.NoSuchResourceException;
import com.example.omdbdemo.common.core.exception.ResourceAlreadyExistsException;
import com.example.omdbdemo.movies.core.model.Movie;
import com.example.omdbdemo.movies.core.usecase.CreateMovieFromTitle;
import com.example.omdbdemo.movies.core.usecase.DeleteMovie;
import com.example.omdbdemo.movies.core.usecase.GetMovie;
import com.example.omdbdemo.movies.core.usecase.UpdateMovie;
import com.example.omdbdemo.movies.entrypoints.dto.CreateMovieByTitleCommand;
import com.example.omdbdemo.movies.entrypoints.dto.MovieDto;
import com.example.omdbdemo.movies.entrypoints.dto.UpdateMovieCommand;
import com.example.omdbdemo.movies.entrypoints.dto.mapper.MovieDtoMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/movies")
@AllArgsConstructor
public class MovieController {
    private final CreateMovieFromTitle createMovieFromTitle;
    private final GetMovie getMovie;
    private final UpdateMovie updateMovie;
    private final DeleteMovie deleteMovie;
    private final MovieDtoMapper mapper;

    private final static Logger logger = LoggerFactory.getLogger(MovieController.class);

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
                .body(mapper.fromDomain(createdMovie));
    }

    @GetMapping(value = "/movies/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDto> getMovieById(@PathVariable String id) throws NoSuchResourceException {
        Movie movie = getMovie.execute(id);
        return ResponseEntity.ok(mapper.fromDomain(movie));
    }

    @PutMapping(
            value = "/movies/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<MovieDto> updateMovie(@RequestBody UpdateMovieCommand movieDtoUpdate, @PathVariable String id)
            throws NoSuchResourceException {
        Movie updateCommand = mapper.toDomain(movieDtoUpdate, id);
        Movie updatedMovie = updateMovie.execute(updateCommand);
        MovieDto updatedMovieDto = mapper.fromDomain(updatedMovie);

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
}
