package com.example.omdbdemo.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"" +
        "com.example.omdbdemo.movies.dataproviders.db.entity.mapper",
        "com.example.omdbdemo.movies.dataproviders.omdb.entity.mapper",
        "com.example.omdbdemo.movies.entrypoints.dto.mapper",
})
@TestConfiguration
public class MapperTestConfiguration {
}

