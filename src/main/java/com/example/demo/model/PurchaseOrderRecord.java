package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.time.LocalDate;



@Entity
public class PurchaseOrderRecord{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String poNumber;
    private Long supplierId;
    private String itemDescription;
    private Long quantity;
    private String promisedDeliveryDate;
    private String issuedDate;
    @Column(name = "expected_date")
    private LocalDate expectedDate;

 

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
    public void setItemDescription(String itemDescription){
        this.itemDescription=itemDescription;
    }
    public Long getQuantity(){
        return quantity;
    }
    public void setQuantity(Long quantity){
        this.quantity=quantity;
    }
    public String getPromisedDeliveryDate(){
        return promisedDeliveryDate;
    }
    public void setPromisedDeliveryDate(String promisedDeliveryDate){
        this.promisedDeliveryDate=promisedDeliveryDate;
    }
    public String getIssuedDate(){
        return issuedDate;
    }
    public void setIssuedDate(String issuedDate){
        this.issuedDate=issuedDate;
    }
    public LocalDate getExpectedDate() {
        return expectedDate;
    }
    public void setExpectedDate(LocalDate expectedDate) {
        this.expectedDate = expectedDate;
    }

    public PurchaseOrderRecord(Long id,String poNumber,String itemDescription,Long quantity,String promisedDeliveryDate,String issuedDate,LocalDate expectedDate){
        this.id=id;
        this.poNumber=poNumber;
        this.supplierId=supplierId;
        this.itemDescription=itemDescription;
        this.quantity=quantity;
        this.promisedDeliveryDate=promisedDeliveryDate;
        this.issuedDate=issuedDate;
        this.expectedDate = expectedDate;
    }
    public PurchaseOrderRecord(){

    }
}