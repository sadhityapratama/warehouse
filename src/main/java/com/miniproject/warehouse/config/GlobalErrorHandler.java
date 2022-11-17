package com.miniproject.warehouse.config;

import com.miniproject.warehouse.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
@Slf4j
public class GlobalErrorHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String BadRequestException(BadRequestException badRequestException){
        log.error("Throwing BadRequestException-> {}", badRequestException.toString());
        return badRequestException.getErrorMessage();
    }

}
