package com.example.demoo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;


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

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getPoNumber(){
        return poNumber;
    }
    public void setPonumber(String poNumber)
    {
        this.poNumber=poNumber;
    }
    public Long getSupplierId(){
        return supplierId;
    }
    public void setSupplierId(Long supplierId){
        this.supplierId=supplierId;
    }
    public String getItemDescription(){
        return itemDescription;
    }
    public void setItemDescription(String itemDescription)[
        this.itemDescription=itemDescription;
    ]
    public Integer q




}