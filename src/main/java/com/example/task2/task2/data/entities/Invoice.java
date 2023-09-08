package com.example.task2.task2.data.entities;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @UpdateTimestamp
    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "date_modified")
    private LocalDateTime dateModified;

    @Column(name = "invoice_date")
    private LocalDate invoiceDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @NotNull
    @Column(name = "custom_date")
    private LocalDate customDate;

    private BigDecimal subtotal;
    private BigDecimal taxes;
    private BigDecimal discounts;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne()
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    /*
    Cascade: all cascade operations should be applied to the associated entities.
    orphanRemoval: means if an associated entity is removed from the relationship (becomes orphaned),
    it will also be deleted from the database.

     */

    // todo: we need the invoice attachments and invoice items, getById is an example

    //The relationship between the invoice and the invoice_item tables
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceItem> invoiceItems = new ArrayList<>();

    //The relationship between the invoice and the invoice_attachment tables
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceAttachment> invoiceAttachments = new ArrayList<>();


//    //The relationship between the invoice and the invoice_audit tables
//    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<InvoiceAudit> invoiceAudits = new ArrayList<>();



    @PrePersist
    public void prePersist() {
        dateCreated = LocalDateTime.now();
        dateModified = LocalDateTime.now();
        invoiceDate = LocalDate.now();
        dueDate = LocalDate.now().plusDays(30);
    }

    @PreUpdate
    public void preUpdate() {
        dateModified = LocalDateTime.now();
    }
}
