package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDate;



@Entity
public class DeliveryRecord{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long poId;
    private LocalDate actualDeliveryDate;
    private Long deliveredQuantity;
    private String notes;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public Long getPoId(){
        return poId;
    }
    public void setPold(Long poId){
        this.poId=poId;
    }
    public LocalDate getActualDeliveryDate(){
        return actualDeliveryDate;
    }
    public void setActualDeliveryDate(LocalDate actualDeliveryDate){
        this.actualDeliveryDate=actualDeliveryDate;
    }
    public Long getDeliveredQuantity(){
        return deliveredQuantity;
    }
    public void setDeliveredQuantity(Long deliveredQuantity){
        this.deliveredQuantity=deliveredQuantity;
    }
    public String getNotes(){
        return notes;
    }
    public void setNotes(String notes){
        this.notes=notes;
    }
    public DeliveryRecord(Long id,Long poId,LocalDate actualDeliveryDate,Long deliveredQuantity,String notes){
        this.id=id;
        this.poId=poId;
        this.actualDeliveryDate=actualDeliveryDate;
        this.deliveredQuantity=deliveredQuantity;
        this.notes=notes;
    }
    public DeliveryRecord(){

    }
}