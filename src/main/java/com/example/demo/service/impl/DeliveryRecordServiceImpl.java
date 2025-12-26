package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.DeliveryRecord;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.repository.PurchaseOrderRecordRepository;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private final DeliveryRecordRepository repo;
    private final PurchaseOrderRecordRepository poRepo;

    public DeliveryRecordServiceImpl(
            DeliveryRecordRepository repo,
            PurchaseOrderRecordRepository poRepo
    ) {
        this.repo = repo;
        this.poRepo = poRepo;
    }

    @Override
    public DeliveryRecord recordDelivery(DeliveryRecord record) {

        if (record.getDeliveredQuantity() < 0) {
            throw new BadRequestException("Delivered quantity must be >= 0");
        }

        poRepo.findById(record.getPoId())
                .orElseThrow(() -> new BadRequestException("Invalid PO id"));

        return repo.save(record);
    }

    @Override
    public List<DeliveryRecord> getDeliveriesByPO(Long poId) {
        return repo.findByPoId(poId);
    }

    @Override
    public List<DeliveryRecord> getAllDeliveries() {
        return repo.findAll();
    }

    @Override
    public Optional<DeliveryRecord> getDeliveryById(Long id) {
        return repo.findById(id);
    }
}
