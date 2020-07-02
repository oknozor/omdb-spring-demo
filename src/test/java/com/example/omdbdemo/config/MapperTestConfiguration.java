package com.example.omdbdemo.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.example.omdbdemo.movies.dataproviders.db.entity.mapper"})
@TestConfiguration
public class MapperTestConfiguration {
}

