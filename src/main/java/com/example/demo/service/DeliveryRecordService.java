package com.example.demo.service;

import com.example.demo.model.DeliveryRecord;
import java.util.List;
import java.util.Optional;

public interface DeliveryRecordService {

    DeliveryRecord recordDelivery(DeliveryRecord record);

    List<DeliveryRecord> getDeliveriesByPO(Long poId);

    Optional<DeliveryRecord> getDeliveryById(Long id);

    List<DeliveryRecord> getAllDeliveries();
}
