package com.example.omdbdemo.movies.dataproviders.omdb.repository;

import com.example.omdbdemo.movies.dataproviders.omdb.entity.OmdbMovieEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "movie", url = "http://www.omdbapi.com", configuration = OmdbClientConfig.class)
public interface OmdbClient {
    @RequestMapping(method = RequestMethod.GET, value = "/?t={title}")
    OmdbMovieEntity getMovieByTitle(@PathVariable("title") String title);
}