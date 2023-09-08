package com.example.task2.task2.controllers.DTOs;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SellerDTO {
    private Long sellerId;
    private String sellerName;
    private String sellerAddress;
    private String sellerPhone;
    private String sellerEmail;
    private String sellerWebsite;
}

