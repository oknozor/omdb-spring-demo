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
import com.example.omdbdemo.movies.entrypoints.dto.mapper.MovieDtoMapper;
import com.example.omdbdemo.movies.entrypoints.dto.UpdateMovieCommand;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class MovieController {
    private final CreateMovieFromTitle createMovieFromTitle;
    private final GetMovie getMovie;
    private final UpdateMovie updateMovie;
    private final DeleteMovie deleteMovie;
    private final MovieDtoMapper mapper;

    @PostMapping("/movies")
    public ResponseEntity<MovieDto> create(
            @RequestBody CreateMovieByTitleCommand command
    ) throws NoSuchResourceException, ResourceAlreadyExistsException {
        String title = command.getTittle();
        Movie createdMovie = createMovieFromTitle.execute(title);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.fromDomain(createdMovie));
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<MovieDto> getById(@PathVariable String id) throws NoSuchResourceException {
        Movie movie = getMovie.execute(id);
        return ResponseEntity.ok(mapper.fromDomain(movie));
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<MovieDto> update(@RequestBody UpdateMovieCommand movieDtoUpdate, @PathVariable String id)
            throws NoSuchResourceException {
        Movie updateCommand = mapper.toDomain(movieDtoUpdate, id);
        Movie updatedMovie = updateMovie.execute(updateCommand);
        MovieDto updatedMovieDto = mapper.fromDomain(updatedMovie);

        return ResponseEntity.ok(updatedMovieDto);
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> update(@PathVariable String id) throws NoSuchResourceException {
        deleteMovie.execute(id);

        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping("/movies")
    public List<MovieDto> getAll() {
        System.out.println("get all movie not implemented");
        return null;
    }
}
