package com.example.demo.model;

public class SupplierProfile {

    private Long id;
    private String code;
    private String name;
    private String email;
    private boolean active;

    public SupplierProfile() {
    }

    public SupplierProfile(Long id, String code, boolean active) {
        this.id = id;
        this.code = code;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
