package com.example.task2.task2.controllers.DTOs;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class InvoiceItemDTO {
    private Integer id;
    private Integer invoiceId;
    private String description;
    private Integer quantity;
    private BigDecimal unitPrice;
}
