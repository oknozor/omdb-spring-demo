package com.example.omdbdemo.movies.dataproviders.omdb.repository;

import com.example.omdbdemo.movies.dataproviders.omdb.entity.OmdbMovieEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class OmdbClient {
    private final String apiKey;
    private final RestTemplate restTemplate;

    public OmdbClient(@Value("${omdb.api.key}") String apiKey) {
        this.apiKey = apiKey;
        restTemplate = new RestTemplate();
        System.out.println("this is " + apiKey);
    }

    public Optional<OmdbMovieEntity> getMovieByTitle(String title) {
        ResponseEntity<OmdbMovieEntity> response = restTemplate
                .exchange("http://www.omdbapi.com/?apikey={apikey}&t={title}",
                        HttpMethod.GET, HttpEntity.EMPTY, OmdbMovieEntity.class, apiKey, title);

        // When a movie is not found Omdb api returns HTTP 200 and expose the 404 error in the body.
        // Hence this dirty trick
        if (response.getBody() != null && response.getBody().getId() == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(response.getBody());
    }
}