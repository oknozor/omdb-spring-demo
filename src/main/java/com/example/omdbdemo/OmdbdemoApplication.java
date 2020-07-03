package com.example.omdbdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OmdbdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmdbdemoApplication.class, args);
	}

}
