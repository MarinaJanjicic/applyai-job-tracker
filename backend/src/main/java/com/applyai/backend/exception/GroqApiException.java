package com.applyai.backend.exception;

public class GroqApiException extends RuntimeException{

    public GroqApiException(String message){
        super(message);
    }
}
