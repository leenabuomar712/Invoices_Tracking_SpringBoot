package com.example.task2.task2.service;
import com.example.task2.task2.data.entities.InvoiceAudit;
import com.example.task2.task2.data.Repositories.InvoiceAuditRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InvoiceAuditService {

    private final InvoiceAuditRepository invoiceAuditRepository;

    public InvoiceAuditService(InvoiceAuditRepository invoiceAuditRepository) {
        this.invoiceAuditRepository = invoiceAuditRepository;
    }

    public InvoiceAudit updateInvoiceAudit(InvoiceAudit updatedInvoiceAudit) {
        return invoiceAuditRepository.save(updatedInvoiceAudit);
    }
}