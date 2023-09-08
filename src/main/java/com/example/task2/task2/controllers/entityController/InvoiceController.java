package com.example.task2.task2.controllers.entityController;

import com.example.task2.task2.ConcreteErrorResponse;
import com.example.task2.task2.NotFoundException;
import com.example.task2.task2.controllers.DTOs.InvoiceDTO;
import com.example.task2.task2.controllers.DTOs.InvoicesByIdDTO;
import com.example.task2.task2.data.Repositories.InvoiceRepository;
import com.example.task2.task2.data.entities.Invoice;
import com.example.task2.task2.service.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private final InvoiceService invoiceService;

    @Autowired
    private final SellerService sellerService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final InvoiceItemService invoiceItemService;

    @Autowired
    private final InvoiceAttachmentService invoiceAttachmentService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    public InvoiceController(InvoiceService invoiceService, SellerService sellerService, UserService userService, InvoiceItemService invoiceItemService, InvoiceAttachmentService invoiceAttachmentService, InvoiceRepository invoiceRepository) {
        this.invoiceService = invoiceService;
        this.sellerService = sellerService;
        this.userService = userService;
        this.invoiceItemService = invoiceItemService;
        this.invoiceAttachmentService = invoiceAttachmentService;
        this.invoiceRepository = invoiceRepository;
    }


    @GetMapping
    public ResponseEntity<Page<InvoiceDTO>> getAllInvoices(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "dateCreated,desc") String sort) {
        Pageable pageable = PageRequest.of(page, size, getSort(sort));

        Page<Invoice> invoicesPage = invoiceService.getAllInvoicesPaginatedWithSellers(pageable);

        if (invoicesPage.hasContent()) {
            List<InvoiceDTO> invoiceDTOs = invoicesPage
                    .map(invoice -> modelMapper.map(invoice, InvoiceDTO.class))
                    .getContent();
            return ResponseEntity.ok(new PageImpl<>(invoiceDTOs, pageable, invoicesPage.getTotalElements()));
        } else {
            return ResponseEntity.noContent().build(); // response = 204 No Content
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getInvoiceById(@PathVariable(name = "id") Long id) {
        try {
            Invoice invoice = invoiceService.getInvoiceById(id);
            InvoicesByIdDTO invoiceResponse = modelMapper.map(invoice, InvoicesByIdDTO.class);
            return new ResponseEntity<>(invoiceResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            return invoiceService.handleInvoiceNotFoundException(id);
        }
    }

    @PostMapping
    public ResponseEntity<InvoiceDTO> createInvoice(@RequestBody @Valid InvoiceDTO invoiceDTO) {
        // Convert DTO to entity
        Invoice invoiceRequest = modelMapper.map(invoiceDTO, Invoice.class);

        // Use the repository to save the entity
        Invoice savedInvoice = invoiceRepository.save(invoiceRequest);

        // Convert entity to DTO
        InvoiceDTO invoiceResponse = modelMapper.map(savedInvoice, InvoiceDTO.class);

        return new ResponseEntity<>(invoiceResponse, HttpStatus.CREATED);
    }


    // change the request for DTO
    // change the response for DTO
    // TODO: id should always be sent as path variable
    // todo: path variable vs request parameter
    @PutMapping("/{id}")
    public ResponseEntity<?> updateInvoice(@PathVariable long id, @RequestBody InvoiceDTO invoiceDto) {

        // convert DTO to Entity
        Invoice invoiceRequest = modelMapper.map(invoiceDto, Invoice.class);

        try {
            Optional<Invoice> invoice = invoiceService.updateInvoice(id, invoiceRequest);

            InvoiceDTO invoiceResponse = modelMapper.map(invoice, InvoiceDTO.class);
            return new ResponseEntity<>(invoiceResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            return invoiceService.handleInvoiceNotFoundException(id);
        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable(name = "id") Long id) {
        try {
            invoiceService.deleteInvoice(id);
            return ResponseEntity.noContent().build(); // 204 successful with empty body
        } catch (NotFoundException ex) {
            ConcreteErrorResponse errorResponse = new ConcreteErrorResponse(404, "Not Found", ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorResponse); // 404 not found with JSON error response
        }
    }

    private Sort getSort(String sort) {
        String[] split = sort.split(",");
        String sortBy = split[0];
        String sortOrder = split[1];

        if ("asc".equalsIgnoreCase(sortOrder)) {
            return Sort.by(sortBy).ascending();
        } else {
            return Sort.by(sortBy).descending();
        }
    }

}
