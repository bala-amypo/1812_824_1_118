package com.example.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.DeliveryRecord;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.DeliveryRecordService;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    @Autowired
    private DeliveryRecordRepository repo;

    @Override
    public DeliveryRecord recordDelivery(DeliveryRecord delivery) {
        return repo.save(delivery);
    }

    @Override
    public List<DeliveryRecord> getDeliveriesByPO(Long poId) {
        return repo.findByPoId(poId);
    }

    @Override
    public DeliveryRecord getDeliveryById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<DeliveryRecord> getAllDeliveries() {
        return repo.findAll();
    }
}
