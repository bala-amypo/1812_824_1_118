package com.example.demo.model;

import java.time.LocalDate;

public class DeliveryRecord {

    private Long id;
    private Long purchaseOrderId;
    private Integer quantity;
    private LocalDate actualDeliveryDate;

    public DeliveryRecord() {
    }

    public DeliveryRecord(Long id, Long purchaseOrderId, Integer quantity) {
        this.id = id;
        this.purchaseOrderId = purchaseOrderId;
        this.quantity = quantity;
        this.actualDeliveryDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(LocalDate actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }
}
