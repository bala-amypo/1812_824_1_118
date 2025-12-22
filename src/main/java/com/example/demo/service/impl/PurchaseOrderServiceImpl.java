package com.example.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.PurchaseOrderRecordRepository;
import com.example.demo.repository.SupplierProfileRepository;
import com.example.demo.service.PurchaseOrderService;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    private PurchaseOrderRecordRepository poRepo;

    @Autowired
    private SupplierProfileRepository supplierRepo;

    @Override
    public PurchaseOrderRecord createPurchaseOrder(PurchaseOrderRecord po) {

        SupplierProfile supplier = supplierRepo.findById(po.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Invalid supplierId"));

        if (!supplier.isActive()) {
            throw new RuntimeException("Supplier must be active");
        }

        return poRepo.save(po);
    }

    @Override
    public List<PurchaseOrderRecord> getPOsBySupplier(Long supplierId) {
        return poRepo.findBySupplierId(supplierId);
    }

    @Override
    public PurchaseOrderRecord getPOById(Long id) {
        return poRepo.findById(id).orElse(null);
    }

    @Override
    public List<PurchaseOrderRecord> getAllPurchaseOrders() {
        return poRepo.findAll();
    }
}
