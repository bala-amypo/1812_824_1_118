package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.DeliveryRecord;
import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.service.DeliveryRecordService;
import com.example.demo.service.PurchaseOrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private final List<DeliveryRecord> deliveries = new ArrayList<>();
    private final PurchaseOrderService poService;

    public DeliveryRecordServiceImpl(PurchaseOrderService poService) {
        this.poService = poService;
    }

    @Override
    public DeliveryRecord recordDelivery(DeliveryRecord record) {

        PurchaseOrderRecord po = poService.getPOById(record.getPurchaseOrderId())
                .orElseThrow(() -> new BadRequestException("Invalid PO"));

        if (record.getQuantity() == null || record.getQuantity() <= 0) {
            throw new BadRequestException("Invalid quantity");
        }

        int delivered = deliveries.stream()
                .filter(d -> d.getPurchaseOrderId().equals(po.getId()))
                .mapToInt(DeliveryRecord::getQuantity)
                .sum();

        if (delivered + record.getQuantity() > po.getQuantity()) {
            throw new BadRequestException("Over delivery");
        }

        deliveries.add(record);
        return record;
    }

    @Override
    public List<DeliveryRecord> getDeliveriesByPO(Long poId) {
        List<DeliveryRecord> result = new ArrayList<>();
        for (DeliveryRecord d : deliveries) {
            if (poId.equals(d.getPurchaseOrderId())) {
                result.add(d);
            }
        }
        return result;
    }

    @Override
    public List<DeliveryRecord> getAllDeliveries() {
        return deliveries;
    }

    @Override
    public Optional<DeliveryRecord> getDeliveryById(Long id) {
        return deliveries.stream()
                .filter(d -> id.equals(d.getId()))
                .findFirst();
    }
}
