package com.example.task2.task2;

public class ConcreteErrorResponse extends ErrorResponse {
    public ConcreteErrorResponse(int status, String error, String message) {
        super(status, error, message);
    }
}