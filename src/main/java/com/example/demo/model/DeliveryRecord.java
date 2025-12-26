package com.example.demo.model;

import java.time.LocalDate;

public class DeliveryRecord {

    private Long id;
    private Long poId;
    private int deliveredQuantity;
    private LocalDate actualDeliveryDate;

    // ----- getters & setters -----

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // REQUIRED BY TESTS
    public Long getPoId() {
        return poId;
    }

    public void setPoId(Long poId) {
        this.poId = poId;
    }

    // REQUIRED BY TESTS
    public int getDeliveredQuantity() {
        return deliveredQuantity;
    }

    public void setDeliveredQuantity(int deliveredQuantity) {
        this.deliveredQuantity = deliveredQuantity;
    }

    // REQUIRED BY DelayScore tests
    public LocalDate getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(LocalDate actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }
}
