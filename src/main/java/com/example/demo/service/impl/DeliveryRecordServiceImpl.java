package com.example.demo.service.impl;

import com.example.demo.model.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;
import com.example.demo.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private static final Map<Long, DeliveryRecord> store = new HashMap<>();
    private static long seq = 1;

    @Autowired
    private PurchaseOrderService poService;

    @Override
    public DeliveryRecord recordDelivery(DeliveryRecord record) {

        if (record.getDeliveredQuantity() <= 0) {
            throw new RuntimeException("Invalid delivery quantity");
        }

        if (poService.getPOById(record.getPoId()).isEmpty()) {
            throw new RuntimeException("Purchase order not found");
        }

        record.setId(seq++);
        store.put(record.getId(), record);
        return record;
    }

    @Override
    public List<DeliveryRecord> getDeliveriesByPO(Long poId) {
        return store.values().stream()
                .filter(d -> poId.equals(d.getPoId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DeliveryRecord> getAllDeliveries() {
        return new ArrayList<>(store.values());
    }

    @Override
    public DeliveryRecord getDeliveryById(Long id) {
        DeliveryRecord record = store.get(id);
        if (record == null) {
            throw new RuntimeException("Delivery not found");
        }
        return record;
    }
}
