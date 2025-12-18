package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.time.LocalDateTime;

@Entity
public class SupplierRiskAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long supplierId;

    @Column(nullable = false)
    private String alertLevel; // LOW / MEDIUM / HIGH

    private String message;

    private LocalDateTime alertDate;

    private Boolean resolved = false; // default false

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getAlertLevel() {
        return alertLevel;
    }
    public void setAlertLevel(String alertLevel) {
        this.alertLevel = alertLevel;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getAlertDate() {
        return alertDate;
    }
    public void setAlertDate(LocalDateTime alertDate) {
        this.alertDate = alertDate;
    }

    public Boolean getResolved() {
        return resolved;
    }
    public void setResolved(Boolean resolved) {
        this.resolved = resolved;
    }

    public SupplierRiskAlert(
            Long id,
            Long supplierId,
            String alertLevel,
            String message,
            LocalDateTime alertDate,
            Boolean resolved) {

        this.id = id;
        this.supplierId = supplierId;
        this.alertLevel = alertLevel;
        this.message = message;
        this.alertDate = alertDate;
        this.resolved = resolved;
    }
}
