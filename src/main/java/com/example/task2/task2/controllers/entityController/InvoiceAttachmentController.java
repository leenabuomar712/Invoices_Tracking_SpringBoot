package com.example.task2.task2.controllers.entityController;

import com.example.task2.task2.controllers.DTOs.InvoiceAttachmentDTO;
import com.example.task2.task2.controllers.DTOs.InvoiceDTO;
import com.example.task2.task2.data.entities.InvoiceAttachment;
import com.example.task2.task2.service.InvoiceAttachmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/invoice-attachments")
public class InvoiceAttachmentController {
    @Autowired
    private ModelMapper modelMapper;
    private final InvoiceAttachmentService invoiceAttachmentService;

    public InvoiceAttachmentController(InvoiceAttachmentService invoiceAttachmentService) {
        this.invoiceAttachmentService = invoiceAttachmentService;
    }

    @GetMapping
    public List<InvoiceAttachmentDTO> getAllInvoiceAttachments() {
        return invoiceAttachmentService.getAllInvoiceAttachments().stream().map(invoiceAttachment -> modelMapper.map(invoiceAttachment, InvoiceAttachmentDTO.class)).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceAttachmentDTO> getInvoiceById(@PathVariable(name = "id") Long id) {
        InvoiceAttachment invoiceAttachment = invoiceAttachmentService.getInvoiceAttachmentById(id);

        // convert entity to DTO
        InvoiceAttachmentDTO invoiceResponse = modelMapper.map(invoiceAttachment, InvoiceAttachmentDTO.class);
        return ResponseEntity.ok().body(invoiceResponse);
    }

    @PostMapping
    public ResponseEntity<InvoiceDTO> createInvoiceAttachment(@RequestBody InvoiceAttachmentDTO invoiceAttachmentDTO) {

        // convert DTO to entity
        InvoiceAttachment invoiceAttachmentRequest = modelMapper.map(invoiceAttachmentDTO, InvoiceAttachment.class);

        InvoiceAttachment invoiceAttachment = (InvoiceAttachment) invoiceAttachmentService.createInvoiceAttachment((InvoiceAttachment) invoiceAttachmentRequest);
        // convert entity to DTO
        InvoiceDTO invoiceAttachmentResponse = modelMapper.map(invoiceAttachment, InvoiceDTO.class);

        return new ResponseEntity<InvoiceDTO>(invoiceAttachmentResponse, HttpStatus.CREATED);
    }

    // change the request for DTO
    // change the response for DTO
    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDTO> updateInvoiceAttachment(@PathVariable long id, @RequestBody InvoiceAttachmentDTO invoiceAttachmentDTO) {

        // convert DTO to Entity
        InvoiceAttachment invoiceAttachmentRequest = modelMapper.map(invoiceAttachmentDTO, InvoiceAttachment.class);

        InvoiceAttachment invoiceAttachment = invoiceAttachmentService.updateInvoiceAttachment(id, invoiceAttachmentRequest);
        // entity to DTO
        InvoiceDTO invoiceResponse = modelMapper.map(invoiceAttachment, InvoiceDTO.class);
        return ResponseEntity.ok().body(invoiceResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInvoiceAttachment(@PathVariable(name = "id") Long id) {
        boolean deleted = invoiceAttachmentService.deleteInvoiceAttachment(id);
        if (deleted) {
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

}
