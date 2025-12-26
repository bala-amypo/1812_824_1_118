package com.example.demo.model;

public class SupplierProfile {

    private Long id;
    private String supplierCode;
    private String supplierName;
    private boolean active = true;

    // ----- getters & setters -----

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // REQUIRED BY TESTS
    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    // REQUIRED BY TESTS
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    // REQUIRED BY TESTS
    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
