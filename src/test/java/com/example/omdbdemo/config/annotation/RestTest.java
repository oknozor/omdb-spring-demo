package com.example.omdbdemo.config.annotation;


import com.example.omdbdemo.config.MapperTestConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@ContextConfiguration(classes = {MapperTestConfiguration.class})
@EnableSpringDataWebSupport
@AutoConfigureRestDocs(outputDir = "target/snippets")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RestTest {
}
