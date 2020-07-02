package com.example.omdbdemo.common.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NoSuchResourceException extends BusinessException {
    public NoSuchResourceException(Class clazz, Object id) {
        super(String.format("%s not found with id %s", clazz.getSimpleName(), id != null ? id.toString() : ""), HttpStatus.NOT_FOUND);
    }
}
