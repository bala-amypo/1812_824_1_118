package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.DeliveryRecord;
import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.service.DeliveryRecordService;
import com.example.demo.service.PurchaseOrderService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private static final Map<Long, DeliveryRecord> store = new HashMap<>();
    private static long seq = 1;

    private final PurchaseOrderService poService;

    public DeliveryRecordServiceImpl(PurchaseOrderService poService) {
        this.poService = poService;
    }

    @Override
    public DeliveryRecord recordDelivery(DeliveryRecord record) {

        if (record.getDeliveredQuantity() <= 0) {
            return null;
        }

        // Optional-returning service
        Optional<PurchaseOrderRecord> po =
                poService.getPOById(record.getPoId());

        if (po.isEmpty()) {
            return null;
        }

        record.setId(seq++);
        store.put(record.getId(), record);
        return record;
    }

    @Override
    public Optional<DeliveryRecord> getDeliveryById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<DeliveryRecord> getDeliveriesByPO(Long poId) {
        return store.values()
                .stream()
                .filter(d -> poId.equals(d.getPoId()))
                .toList();
    }

    @Override
    public List<DeliveryRecord> getAllDeliveries() {
        return new ArrayList<>(store.values());
    }
}
