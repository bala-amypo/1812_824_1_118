package com.example.demo.service.impl;

import com.example.demo.model.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private final List<DeliveryRecord> store = new ArrayList<>();

    @Override
    public DeliveryRecord recordDelivery(DeliveryRecord record) {

        if (record.getPoId() == null) {
            throw new RuntimeException("PO Id is required");
        }

        if (record.getDeliveredQuantity() <= 0) {
            throw new RuntimeException("Quantity must be positive");
        }

        store.add(record);
        return record;
    }

    @Override
    public List<DeliveryRecord> getDeliveriesByPO(Long poId) {
        List<DeliveryRecord> result = new ArrayList<>();
        for (DeliveryRecord d : store) {
            if (poId.equals(d.getPoId())) {
                result.add(d);
            }
        }
        return result;
    }

    @Override
    public List<DeliveryRecord> getAllDeliveries() {
        return store;
    }

    @Override
    public Optional<DeliveryRecord> getDeliveryById(Long id) {
        return store.stream()
                .filter(d -> id.equals(d.getId()))
                .findFirst();
    }
}
