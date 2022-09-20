package com.api.exceptions;

import com.api.entity.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice("com.api")
@Slf4j
public class GlobalApiExceptionHandler extends ResponseEntityExceptionHandler {

    // Handling Specific Exception
    @ExceptionHandler(value = {})
    public ResponseEntity<Object> handleExceptions(ApiException exception, WebRequest webRequest) {
        log.info("GLOBAL EXCEPTION:: Handling custom error. ");
        ResponseEntity<Object> entity = new ResponseEntity<>(new ApiResponse<>(List.of(exception)),
                HttpStatus.NO_CONTENT);
        return entity;
    }

    // Handling Global Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception exception,
                                                        WebRequest webRequest) {
        log.info("GLOBAL EXCEPTION:: Handling global error. ");
        return new ResponseEntity<>(new ApiResponse<>(null, List.of(exception),
                webRequest.getDescription(true)),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
