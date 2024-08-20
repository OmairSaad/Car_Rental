package com.example.carrental.exception;

import java.util.HashMap;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(resourceNotfound.class)
    public ResponseEntity<?> not(resourceNotfound ex){
        ExceptionResponse res = new ExceptionResponse(ex.getMessage(), false);
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> notvalid(MethodArgumentNotValidException ex){
        HashMap<String , String> err= new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(e->{
            String error = ((FieldError)e).getField();
            String message = e.getDefaultMessage();
            err.put(error, message);
        });
        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmailExistException.class)
    public ResponseEntity<?> exist(EmailExistException ex){
        return new ResponseEntity<>(new ExceptionResponse("Email Exists: "+ex.getMessage(),false), HttpStatus.NOT_ACCEPTABLE);
    }
}

