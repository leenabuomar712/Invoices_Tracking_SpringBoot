package com.example.task2.task2.service;

import com.example.task2.task2.data.Repositories.InvoiceItemRepository;
import com.example.task2.task2.data.entities.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceItemService {
    private final InvoiceItemRepository invoiceItemRepository;


    @Autowired
    public InvoiceItemService(InvoiceItemRepository invoiceItemRepository ) {
        this.invoiceItemRepository = invoiceItemRepository;
    }
    public List<InvoiceItem> getAllInvoiceItems() {
       return invoiceItemRepository.findAll();

     /*   Optional<Object> invoiceItems = getAllInvoiceItems();
        if (invoiceItems.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(invoiceItems);
        }*/
    }
    public boolean deleteInvoiceItem(long id) {
        try {
            invoiceItemRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<InvoiceItem> getInvoiceItemById(long id) {
        return invoiceItemRepository.findById(id);
    }
    public List<InvoiceItem> getInvoiceItemsByInvoiceId(Long invoiceId) {
        return invoiceItemRepository.findByInvoiceId(invoiceId);
    }

    public InvoiceItem updateInvoiceItem(long id, InvoiceItem updatedItem) {
        Optional<InvoiceItem> optionalItem = invoiceItemRepository.findById(id);
        if (optionalItem.isPresent()) {
            InvoiceItem currentItem = optionalItem.get();
            return invoiceItemRepository.save(currentItem);
        }
        return null;
    }



    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItemRequest) {
        return invoiceItemRepository.save(invoiceItemRequest);
    }
}

