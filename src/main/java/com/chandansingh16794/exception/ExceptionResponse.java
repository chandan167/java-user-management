package com.chandansingh16794.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse<T> {
    private int status;
    private String message;
    private String errorCode;
    private LocalDateTime dateTime;
    T validationError;
}