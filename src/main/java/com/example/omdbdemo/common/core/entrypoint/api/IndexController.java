package com.example.omdbdemo.common.core.entrypoint.api;

import com.example.omdbdemo.comments.entrypoints.CommentController;
import com.example.omdbdemo.movies.entrypoints.MovieController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/")
public class IndexController {

    static class CustomRepresentationModel extends RepresentationModel<CustomRepresentationModel> {
        public CustomRepresentationModel() {
            super(List.of(linkTo(methodOn(MovieController.class).getAllMovies()).withRel("movies"),
                    linkTo(methodOn(CommentController.class).getAllComments()).withRel("comments")));
        }
    }

    @GetMapping
    public CustomRepresentationModel movie() {
        return new CustomRepresentationModel();
    }
}