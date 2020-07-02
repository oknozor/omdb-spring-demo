package com.example.omdbdemo.config;

import org.testcontainers.containers.PostgreSQLContainer;

public class SharedPostgresqlContainer extends PostgreSQLContainer<SharedPostgresqlContainer> {
    private static final String IMAGE_VERSION = "postgres:12.3";
    private static SharedPostgresqlContainer container;

    private SharedPostgresqlContainer() {
        super(IMAGE_VERSION);
    }

    public static SharedPostgresqlContainer getInstance() {
        if (container == null) {
            container = new SharedPostgresqlContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}
