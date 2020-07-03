package com.example.omdbdemo;

import com.example.omdbdemo.config.SharedPostgresqlContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
class OmdbdemoApplicationTests {

	@Container
	public static PostgreSQLContainer<?> postgreSQLContainer = SharedPostgresqlContainer.getInstance();

	@Test
	@DisplayName("Application should start")
	void contextLoads() {
	}

}
