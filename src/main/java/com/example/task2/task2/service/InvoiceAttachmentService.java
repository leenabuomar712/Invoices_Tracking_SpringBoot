package com.example.task2.task2.service;

import com.example.task2.task2.data.Repositories.InvoiceAttachmentRepository;
import com.example.task2.task2.data.entities.InvoiceAttachment;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceAttachmentService {
    private final InvoiceAttachmentRepository invoiceAttachmentRepository;


    public InvoiceAttachmentService(InvoiceAttachmentRepository invoiceAttachmentRepository) {
        this.invoiceAttachmentRepository = invoiceAttachmentRepository;
    }

    public List<InvoiceAttachment> getAllInvoiceAttachments() {
        return invoiceAttachmentRepository.findAll();
    }

    public InvoiceAttachment createInvoiceAttachment(InvoiceAttachment invoiceAttachment) {
        return invoiceAttachmentRepository.save(invoiceAttachment);
    }

    public InvoiceAttachment updateInvoiceAttachment(long id, InvoiceAttachment invoiceRequest) {
        InvoiceAttachment invoiceAttachment = invoiceAttachmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("InvoiceAttachment"));
        invoiceAttachment.setFileName(invoiceRequest.getFileName());
        invoiceAttachment.setFileType(invoiceRequest.getFileType());
        invoiceAttachment.setStoragePath(invoiceAttachment.getStoragePath());
        invoiceAttachment.setInvoice(invoiceAttachment.getInvoice());

        return invoiceAttachmentRepository.save(invoiceAttachment);
    }

    public boolean deleteInvoiceAttachment(long id) {
        InvoiceAttachment invoiceAttachment = invoiceAttachmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("InvoiceAttachment"));

        invoiceAttachmentRepository.delete(invoiceAttachment);
        return false;
    }

    public InvoiceAttachment getInvoiceAttachmentById(long id) {
        Optional<InvoiceAttachment> result = invoiceAttachmentRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new ResourceNotFoundException("InvoiceAttachment");
        }
    }

}
