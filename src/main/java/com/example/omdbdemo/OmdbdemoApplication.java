package com.example.omdbdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@SpringBootApplication
public class OmdbdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmdbdemoApplication.class, args);
	}

	@Bean
	public Formatter<LocalDate> localDateFormatter() {
		return new Formatter<>() {
			@Override
			public LocalDate parse(String text, Locale locale) throws ParseException {
				return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}

			@Override
			public String print(LocalDate object, Locale locale) {
				return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(object);
			}
		};
	}
}
