package com.example.demo.model;

public class SupplierProfile {

    private Long id;
    private String supplierCode;
    private String name;
    private String email;
    private boolean active;

    public SupplierProfile() {
    }

    public SupplierProfile(Long id, String supplierCode, boolean active) {
        this.id = id;
        this.supplierCode = supplierCode;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public boolean getActive() {
        return active;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
