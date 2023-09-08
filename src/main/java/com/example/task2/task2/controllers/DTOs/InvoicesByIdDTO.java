package com.example.task2.task2.controllers.DTOs;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class InvoicesByIdDTO extends InvoiceDTO{
    private List<InvoiceItemDTO> invoiceItems;
    private List<InvoiceAttachmentDTO> invoiceAttachments;
}
