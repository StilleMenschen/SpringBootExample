package com.example.demo.controller;

import com.example.demo.domain.ApiExceptionResponse;
import com.example.demo.lang.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class ExceptionHandlerController {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ApiExceptionResponse> handlerCustomException(CustomException exception) {
        log.error(exception.getMessage(), exception);
        ApiExceptionResponse response = new ApiExceptionResponse(
                exception.getMessage(), exception.getHttpStatus(), ZonedDateTime.now()
        );
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiExceptionResponse> handlerAnonymousException(Exception exception) {
        log.error(exception.getMessage(), exception);
        ApiExceptionResponse response = new ApiExceptionResponse(
                exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, ZonedDateTime.now()
        );
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
