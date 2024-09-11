package com.example.demo.exceptions;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -1053822106864647345L;
    
    private String message;

    public BadRequestException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
