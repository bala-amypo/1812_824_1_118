package com.example.demo.service.impl;

import com.example.demo.model.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private final List<DeliveryRecord> deliveries = new ArrayList<>();

    @Override
    public DeliveryRecord recordDelivery(DeliveryRecord record) {
        deliveries.add(record);
        return record;
    }

    @Override
    public List<DeliveryRecord> getDeliveriesByPo(Long poId) {
        List<DeliveryRecord> result = new ArrayList<>();
        for (DeliveryRecord d : deliveries) {
            if (poId.equals(d.getPoId())) {
                result.add(d);
            }
        }
        return result;
    }

    @Override
    public List<DeliveryRecord> getAllDeliveries() {
        return deliveries;
    }
}
