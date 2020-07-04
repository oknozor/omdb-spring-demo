package com.example.omdbdemo.common.core.entrypoint.api.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ErrorDto {
    private LocalDateTime date;
    private String code;
    private String message;
    private String details;
}
