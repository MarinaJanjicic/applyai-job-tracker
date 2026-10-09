package com.applyai.backend.exception;

public class JobApplicationNotFoundException extends RuntimeException{

    public JobApplicationNotFoundException(String message) {
        super(message);
    }
}
