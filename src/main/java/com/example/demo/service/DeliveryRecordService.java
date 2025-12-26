package com.example.demo.service;

import com.example.demo.model.DeliveryRecord;

import java.util.List;
import java.util.Optional;

public interface DeliveryRecordService {

    DeliveryRecord recordDelivery(DeliveryRecord delivery);

    Optional<DeliveryRecord> getDeliveryById(Long id);

    List<DeliveryRecord> getDeliveriesByPO(Long poId);

    List<DeliveryRecord> getAllDeliveries();
}
