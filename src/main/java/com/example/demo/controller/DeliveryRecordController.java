package com.example.demo.controller;

import com.example.demo.model.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryRecordController {

    private final DeliveryRecordService service;

    public DeliveryRecordController(DeliveryRecordService service) {
        this.service = service;
    }

    @PostMapping
    public DeliveryRecord create(@RequestBody DeliveryRecord delivery) {
        return service.recordDelivery(delivery);
    }

    @GetMapping("/{id}")
public DeliveryRecord getById(@PathVariable Long id) {
    return service.getDeliveryById(id)
            .orElseThrow(() -> new RuntimeException("Delivery not found"));
}

    @GetMapping("/po/{poId}")
    public List<DeliveryRecord> getByPo(@PathVariable Long poId) {
        return service.getDeliveriesByPO(poId);
    }

    @GetMapping
    public List<DeliveryRecord> getAll() {
        return service.getAllDeliveries();
    }
}
