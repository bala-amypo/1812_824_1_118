package com.example.demo.service.impl;

import com.example.demo.model.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Override
public DeliveryRecord recordDelivery(DeliveryRecord record) {

    if (record.getDeliveredQuantity() == null || record.getDeliveredQuantity() < 0) {
        throw new RuntimeException("Delivered quantity must be >=");
    }

    poRepository.findById(record.getPoId())
            .orElseThrow(() -> new RuntimeException("Invalid PO id"));

    return deliveryRepository.save(record);
}

@Override
public List<DeliveryRecord> getDeliveriesByPO(Long poId) {
    return deliveryRepository.findByPoId(poId);
}

@Override
public List<DeliveryRecord> getAllDeliveries() {
    return deliveryRepository.findAll();
}

