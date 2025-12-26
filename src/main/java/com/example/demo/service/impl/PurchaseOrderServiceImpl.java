package com.example.demo.service.impl;

import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.service.PurchaseOrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final SupplierProfileService supplierService;
    private final List<PurchaseOrderRecord> store = new ArrayList<>();

    public PurchaseOrderServiceImpl(SupplierProfileService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public PurchaseOrderRecord createPurchaseOrder(PurchaseOrderRecord po) {
        SupplierProfile supplier;

        try {
            supplier = supplierService.getSupplierById(po.getSupplierId());
        } catch (Exception e) {
            throw new RuntimeException("BadRequestException");
        }

        if (!Boolean.TRUE.equals(supplier.getActive())) {
            throw new RuntimeException("BadRequestException");
        }

        po.setIssuedDate(LocalDate.now());
        store.add(po);
        return po;
    }

    @Override
    public List<PurchaseOrderRecord> getPOsBySupplier(Long supplierId) {
        return store.stream()
                .filter(po -> supplierId.equals(po.getSupplierId()))
                .toList();
    }

    @Override
    public Optional<PurchaseOrderRecord> getPOById(Long id) {
        return store.stream()
                .filter(po -> id.equals(po.getId()))
                .findFirst();
    }

    @Override
    public List<PurchaseOrderRecord> getAllPurchaseOrders() {
        return store;
    }
}

