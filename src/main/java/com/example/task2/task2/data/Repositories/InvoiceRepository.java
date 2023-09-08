package com.example.task2.task2.data.Repositories;

import com.example.task2.task2.data.entities.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    /*
    @Query for getting the attachements and invoices items tables when clicking on the user id
    @Query2 for getting the sellers table when clicking on the seller_id column in the invoice
     */
    @Query("SELECT i FROM Invoice i JOIN FETCH i.seller")
    Page<Invoice> findAllWithSellers(Pageable pageable);

/*    @Query("SELECT j FROM Invoice j JOIN FETCH j.invoiceAttachments")
    Page<Invoice> findAllWithAttachments(Pageable pageable);*/



}
