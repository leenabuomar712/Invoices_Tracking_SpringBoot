package com.example.task2.task2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Invoice not found")
public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
