package com.example.demoo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
@Entity
public class PurchaseOrderRecord{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String poNumber;
    private Long supplierld;
    private String itemDescription;
    private Integer quantity;
    private String promisedDeliveryDate;
    private String issuedDate;
}