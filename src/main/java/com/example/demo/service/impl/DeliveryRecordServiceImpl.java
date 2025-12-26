package com.example.demo.service.impl;

import com.example.demo.model.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private final PurchaseOrderService poService;
    private final List<DeliveryRecord> deliveries = new ArrayList<>();

    public DeliveryRecordServiceImpl(PurchaseOrderService poService) {
        this.poService = poService;
    }

    @Override
    public DeliveryRecord recordDelivery(DeliveryRecord record) {
        if (record.getQuantity() <= 0) {
            throw new RuntimeException("BadRequestException");
        }

        if (poService.getPOById(record.getPoId()).isEmpty()) {
            throw new RuntimeException("BadRequestException");
        }

        deliveries.add(record);
        return record;
    }

    @Override
    public List<DeliveryRecord> getDeliveriesByPO(Long poId) {
        return deliveries.stream()
                .filter(d -> poId.equals(d.getPoId()))
                .toList();
    }

    @Override
    public Optional<DeliveryRecord> getDeliveryById(Long id) {
        return deliveries.stream()
                .filter(d -> id.equals(d.getId()))
                .findFirst();
    }

    @Override
    public List<DeliveryRecord> getAllDeliveries() {
        return deliveries;
    }
}
