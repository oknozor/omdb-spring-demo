package com.example.omdbdemo.common.core.exception;

import org.springframework.http.HttpStatus;

public class ResourceAlreadyExistsException extends BusinessException {
    public ResourceAlreadyExistsException(Class clazz, Object id) {
        super(String.format("%s already exist in database %s", clazz.getSimpleName(), id != null ? id.toString() : ""), HttpStatus.CONFLICT);
    }
}