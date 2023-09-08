package com.example.task2.task2.controllers.DTOs;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
// todo: add data validation, how to apply validation on dtos,, spring boot
public class InvoiceDTO {
    private Integer id;
    @NotEmpty
    private String title;

    private String description;
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private LocalDate dueDate;
    private LocalDate customDate;
    private BigDecimal subtotal;
    private BigDecimal taxes;
    private BigDecimal discounts;
    private Long userId;
    //private Long sellerId;


    private SellerDTO seller;




}
