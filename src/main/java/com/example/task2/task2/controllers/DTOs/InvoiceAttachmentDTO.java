// todo: separate the controllers and dtos into different packages
package com.example.task2.task2.controllers.DTOs;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class InvoiceAttachmentDTO {
    private Integer id;
    private Integer invoiceId;
    private String fileName;
    private String fileType;
    private String storagePath;
}
