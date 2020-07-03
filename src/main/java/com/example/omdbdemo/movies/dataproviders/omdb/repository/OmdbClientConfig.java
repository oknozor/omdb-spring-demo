package com.example.omdbdemo.movies.dataproviders.omdb.repository;

import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OmdbClientConfig {
    @Value("${omdb.api.key}")
    private String omdbApiKey;

    private final ObjectFactory<HttpMessageConverters> messageConverters = HttpMessageConverters::new;

    @Bean
    Encoder feignEncoder() {
        return new SpringEncoder(messageConverters);
    }

    @Bean
    Decoder feignDecoder() {
        return new SpringDecoder(messageConverters);
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.query("apikey", omdbApiKey);
    }
}