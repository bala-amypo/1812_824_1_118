package com.example.demo.service.impl;

import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.repository.PurchaseOrderRecordRepository;
import com.example.demo.service.PurchaseOrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRecordRepository repo;

    public PurchaseOrderServiceImpl(PurchaseOrderRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public Optional<PurchaseOrderRecord> getPOById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<PurchaseOrderRecord> getAllPurchaseOrders() {
        return repo.findAll();
    }
}
