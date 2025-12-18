package com.example.demo.model;



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
    public Long 

}