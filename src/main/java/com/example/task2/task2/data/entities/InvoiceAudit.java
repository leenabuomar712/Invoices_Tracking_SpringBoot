package com.example.task2.task2.data.entities;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Entity
@Table(name = "invoice_audit")
public class InvoiceAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Integer version = 1; // Initial version
    @Column(name = "change_date", nullable = false)
    private Timestamp changeDate;

    @Column(name = "change_type", nullable = false)
    private String changeType;

    private String description;

    @Column(name = "json_old_image", columnDefinition = "JSON")
    private String jsonOldImage;

    @Column(name = "json_new_image", columnDefinition = "JSON")
    private String jsonNewImage;

    @Column(name = "date_created", nullable = false)
    private Timestamp dateCreated;

    @Column(name = "date_modified", nullable = false)
    private Timestamp dateModified;

    @ManyToOne
    @JoinColumn(name = "invoice_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_invoice_audit_invoice"))
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_invoice_audit_user"))
    private User user;


    public InvoiceAudit() {

    }

    @PrePersist
    protected void onCreate() {
        dateCreated = new Timestamp(System.currentTimeMillis());
        dateModified = dateCreated;
    }

    @PreUpdate
    protected void onUpdate() {
        dateModified = new Timestamp(System.currentTimeMillis());
    }
}
