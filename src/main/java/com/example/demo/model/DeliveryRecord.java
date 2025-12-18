package com.example.demo.model;




@Entity
public class DeliveryRecord{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long pold;
    private String actualDeliveryDate;
    private Long 
}