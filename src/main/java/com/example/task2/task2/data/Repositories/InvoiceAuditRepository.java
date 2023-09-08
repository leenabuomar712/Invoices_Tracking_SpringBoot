package com.example.task2.task2.data.Repositories;

import com.example.task2.task2.data.entities.InvoiceAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceAuditRepository extends JpaRepository<InvoiceAudit, Long> {
}