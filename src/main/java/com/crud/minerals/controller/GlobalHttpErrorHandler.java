package com.crud.minerals.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MineralNotFoundException.class)
    public ResponseEntity<Object> handleTaskNotFoundException(MineralNotFoundException exception) {
        return new ResponseEntity<>("Mineral with given id does not exist.", HttpStatus.BAD_REQUEST);
    }
}
