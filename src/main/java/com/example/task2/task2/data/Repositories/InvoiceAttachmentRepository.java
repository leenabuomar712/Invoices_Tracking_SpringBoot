package com.example.task2.task2.data.Repositories;

import com.example.task2.task2.data.entities.InvoiceAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceAttachmentRepository extends JpaRepository<InvoiceAttachment, Long> {

    //todo: read about jpql
    // todo, read about @Query annotation, native ?, Join fetch (to eagerly fetch related entities) amd how this translated to joins

    @Query(value = "select i from Invoice i join fetch i.user u where u.email = ?1")
    void findInvoiceByUser(String email);
}
