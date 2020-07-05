package com.example.omdbdemo.common.core.entrypoint.api;


import com.example.omdbdemo.common.core.entrypoint.api.dto.ErrorDto;
import com.example.omdbdemo.common.core.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalControllerConfig {
    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerConfig.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorDto> handleBusinessException(BusinessException exception) {
        ErrorDto errorDetails = ErrorDto.builder()
                .date(LocalDateTime.now())
                .message(exception.getMessage())
                .build();

        HttpStatus httpStatus = exception.getHttpStatus();
        logger.error(exception.getMessage(), exception);

        return ResponseEntity.status(httpStatus != null ? httpStatus : HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
    }
}
