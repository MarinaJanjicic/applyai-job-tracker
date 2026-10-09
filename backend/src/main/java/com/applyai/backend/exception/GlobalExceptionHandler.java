package com.applyai.backend.exception;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.applyai.backend.dto.error.ErrorResponse;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExists(
            EmailAlreadyExistsException ex) {

        ErrorResponse error=new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                java.time.LocalDateTime.now()
        );

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationExceptions(
            MethodArgumentNotValidException ex
    ){
        Map<String,String> errors=new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error-> {
            errors.put(error.getField(),error.getDefaultMessage());
        });

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JobApplicationNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleJobApplicationNotFound(
            JobApplicationNotFoundException ex){

        ErrorResponse error=new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                java.time.LocalDateTime.now()
        );
                return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse>  handleUserNotFound(
            UserNotFoundException ex){

        ErrorResponse error=new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                java.time.LocalDateTime.now()
        );

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

}
