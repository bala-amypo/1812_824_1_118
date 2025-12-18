package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class DelayScoreRecord{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long supplied;
    private Long pold;
    private Long delayDays;
    private String delaySeverity;
    private Double score;
    private String computedAt;

    public Long getID(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public Long getSupplied(){
        return supplied;
    }
    public void setSupplied(Long supplied){
        this.supplied=supplied;
    }
    public Long getPold(){
        return pold;
    }
    public void setPold(Long pold){
        this.pold=pold;
    }
    public Long getDelayDays(){
        return delayDays;
    }
    public void setDelaysDays(Long delayDays){
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
    public DelayScoreRecord(Long id,Long supplied,Long pold,Long delayDays,String delaySeverity,Double score,String computedAt){
        this.id=id;
        this.supplied=supplied;
        this.pold=pold;
        this.delayDays=delayDays;
        this.delaySeverity=delaySeverity;
        this.score=score;
        this.computedAt=computedAt;
    }
    public DelayScoreRecord(){

    }

}