package com.setupsnap.setup_snap_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleError(ResourceNotFoundException ex){
    return new ResponseEntity<>( ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> genericException(Exception ex){
        return new ResponseEntity<>("error : " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
