package com.example.task2.task2;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Invoice not found")
public class NotFoundException extends RuntimeException {
    private int status;
    private String error;
    private String message;
    private long timestamp;
    public NotFoundException(String message) {
        super(message);
        this.status = 404;
        this.error = "Not Found";
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }
}