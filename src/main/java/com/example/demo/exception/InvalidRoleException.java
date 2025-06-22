package com.example.demo.exception;

public class InvalidRoleException extends  RuntimeException{
    public InvalidRoleException(String message) {
        super(message);
    }
}
