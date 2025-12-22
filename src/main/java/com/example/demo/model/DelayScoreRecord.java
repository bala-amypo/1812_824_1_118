package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDate;
import jakarta.persistence.Column;


@Entity
public class DelayScoreRecord{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long supplierId;
    private Long poId;
    private Long delayDays;
    private String delaySeverity;
    private Double score;
    private String computedAt;
    @Column(name = "expected_date")
    private LocalDate expectedDate;

    public Long getID(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public Long getSupplierId(){
        return supplierId;
    }
    public void setSupplierId(Long supplierId){
        this.supplierId=supplierId;
    }
    public Long getPoId(){
        return poId;
    }
    public void setPoId(Long poId){
        this.poId=poId;
    }
    public Long getDelayDays(){
        return delayDays;
    }
    public void setDelayDays(Long delayDays){
        this.delayDays=delayDays;
    }
    public String getDelaySeverity(){
        return delaySeverity;
    }
    public void setDelaySeverity(String delaySeverity){
        this.delaySeverity=delaySeverity;
    }
    public Double getScore(){
        return score;
    }
    public void setScore(Double score){
        this.score=score;
    }
    public String getComputedAt(){
        return computedAt;
    }
    public void setComputedAt(String computedAt){
        this.computedAt=computedAt;
    }
    public LocalDate getExpectedDate() {
        return expectedDate;
    }
    public void setExpectedDate(LocalDate expectedDate) {
        this.expectedDate = expectedDate;
    }

    public DelayScoreRecord(Long id,Long supplierId,Long poId,Long delayDays,String delaySeverity,Double score,String computedAt,LocalDate expectedDate){
        this.id=id;
        this.supplierId=supplierId;
        this.poId=poId;
        this.delayDays=delayDays;
        this.delaySeverity=delaySeverity;
        this.score=score;
        this.computedAt=computedAt;
        this.expectedDate=expectedDate;
    }
    public DelayScoreRecord(){

    }

}