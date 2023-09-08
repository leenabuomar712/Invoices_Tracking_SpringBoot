package com.example.task2.task2.service;

import com.example.task2.task2.ConcreteErrorResponse;
import com.example.task2.task2.NotFoundException;
import com.example.task2.task2.data.Repositories.InvoiceRepository;
import com.example.task2.task2.data.entities.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private final InvoiceRepository invoiceRepository;

    // todo: dependency injection, on property level vs constructor level
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> getAllInvoices() {

        // TODO: Taken into consideration the UI, we shouldn't return the items and the attachments
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(Long id) {
        // todo: check if the id exists in the DB

        // todo: return the attachments and items
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(id);
        if (!invoiceOptional.isPresent()) {
            throw new NotFoundException("Invoice not found with id: " + id);
        }
        return invoiceOptional.get();
    }

    public ResponseEntity<?> handleInvoiceNotFoundException(Long id) {
        ConcreteErrorResponse errorResponse = new ConcreteErrorResponse(404, "Not Found", "Invoice not found with id: " + id);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }
    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public Optional<Invoice> updateInvoice(Long id, Invoice updatedInvoice) {
        Optional<Invoice> existingInvoice = invoiceRepository.findById(id);

        if (existingInvoice.isPresent()) {
            updatedInvoice.setId(existingInvoice.get().getId());
            Invoice updated = invoiceRepository.save(updatedInvoice);
            return Optional.of(updated);
        } else {
            throw new NotFoundException("Invoice not found with id: " + id);
        }
    }


//    public Optional<Invoice> updateInvoice(Long id, Invoice updatedInvoice) {
//        Optional<Invoice> existingInvoice = invoiceRepository.findById(id);
//
//        // TODO: Keep a blank line before and after code blocks
//        if (existingInvoice.isPresent()) {
//            updatedInvoice.setId(existingInvoice.get().getId());
//            return Optional.of(invoiceRepository.save(updatedInvoice));
//        }
//
//        // TODO: 404
//        return Optional.empty();
//    }

    //for searching:  request parameter
    public boolean deleteInvoice(Long id) {
        if (!invoiceRepository.existsById(id)) {
            throw new NotFoundException("Invoice not found with id: " + id);
        }

        try {
            invoiceRepository.deleteById(id);
            return true; // Deletion was successful
        } catch (Exception e) {
            return false; // Deletion failed
        }
    }


    public Page<Invoice> getAllInvoicesPaginated(Pageable pageable) {
        return invoiceRepository.findAll(pageable);
    }
    public Page<Invoice> getAllInvoicesPaginatedWithSellers(Pageable pageable) {
        return invoiceRepository.findAllWithSellers(pageable);
    }

}
//
//    public List<Invoice> getAllInvoicesWithSorting(String field){
//        return invoiceRepository.findAll(Sort.by(Sort.Direction.ASC, field));
//    }
//
//    public Page<Invoice> getAllInvoicesWithPagination(int offset, int pagesize){
//        Page<Invoice> invoices = invoiceRepository.findAll(PageRequest.of(offset, pagesize));
//        return invoices;
//
//    }
