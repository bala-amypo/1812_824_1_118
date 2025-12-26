package com.example.demo.controller;

import com.example.demo.model.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryRecordController {

    private final DeliveryRecordService service;

    public DeliveryRecordController(DeliveryRecordService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public DeliveryRecord getById(@PathVariable Long id) {
        return service.getDeliveryById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));
    }
}
