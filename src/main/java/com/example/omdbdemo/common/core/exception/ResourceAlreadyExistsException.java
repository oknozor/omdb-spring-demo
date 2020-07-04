package com.example.omdbdemo.common.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResourceAlreadyExistsException extends BusinessException {
    public<T> ResourceAlreadyExistsException(Class<T> clazz, Object id) {
        super(String.format("%s already exist in database %s", clazz.getSimpleName(), id != null ? id.toString() : ""), HttpStatus.CONFLICT);
    }
}
