package com.example.task2.task2.controllers.entityController;

import com.example.task2.task2.controllers.DTOs.InvoiceItemDTO;
import com.example.task2.task2.data.entities.InvoiceItem;
import com.example.task2.task2.service.InvoiceItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/invoice-items")
public class InvoiceItemController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private final InvoiceItemService invoiceItemService;

    public InvoiceItemController(InvoiceItemService invoiceItemService) {
        this.invoiceItemService = invoiceItemService;
    }

    @GetMapping
    public List<InvoiceItemDTO> getAllInvoiceItems() {
        return invoiceItemService.getAllInvoiceItems().stream()
                .map(invoiceItem -> modelMapper.map(invoiceItem, InvoiceItemDTO.class))
                .collect(Collectors.toList());
    }

/*    @GetMapping("/{id}")
    public ResponseEntity<InvoiceItemDTO> getInvoiceItemById(@PathVariable Long id) {
        Optional<InvoiceItem> invoiceItem = invoiceItemService.getInvoiceItemById(id);

        if (invoiceItem.isPresent()) {
            InvoiceItemDTO invoiceItemResponse = modelMapper.map(invoiceItem.get(), InvoiceItemDTO.class);
            return ResponseEntity.ok().body(invoiceItemResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

    @PostMapping
    public ResponseEntity<InvoiceItemDTO> createInvoiceItem(@RequestBody InvoiceItemDTO invoiceItemDTO) {
        InvoiceItem invoiceItemRequest = modelMapper.map(invoiceItemDTO, InvoiceItem.class);
        InvoiceItem createdInvoiceItem = invoiceItemService.createInvoiceItem(invoiceItemRequest);
        InvoiceItemDTO invoiceItemResponse = modelMapper.map(createdInvoiceItem, InvoiceItemDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceItemResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceItemDTO> updateInvoiceItem(@PathVariable Long id, @RequestBody InvoiceItemDTO invoiceItemDTO) {
        InvoiceItem invoiceItemRequest = modelMapper.map(invoiceItemDTO, InvoiceItem.class);
        InvoiceItem updatedInvoiceItem = invoiceItemService.updateInvoiceItem(id, invoiceItemRequest);

        if (updatedInvoiceItem != null) {
            InvoiceItemDTO invoiceItemResponse = modelMapper.map(updatedInvoiceItem, InvoiceItemDTO.class);
            return ResponseEntity.ok().body(invoiceItemResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInvoiceItem(@PathVariable Long id) {
        boolean deleted = invoiceItemService.deleteInvoiceItem(id);
        if (deleted) {
            return ResponseEntity.ok("Invoice item deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invoice item not found");
        }
    }
}
