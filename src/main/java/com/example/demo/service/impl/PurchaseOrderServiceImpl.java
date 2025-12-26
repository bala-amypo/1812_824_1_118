package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.model.SupplierProfile;
import com.example.demo.service.PurchaseOrderService;
import com.example.demo.service.SupplierProfileService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final List<PurchaseOrderRecord> store = new ArrayList<>();
    private final SupplierProfileService supplierService;

    public PurchaseOrderServiceImpl(SupplierProfileService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public PurchaseOrderRecord createPurchaseOrder(PurchaseOrderRecord po) {

        SupplierProfile supplier = supplierService
                .getBySupplierId(po.getSupplierId())
                .orElseThrow(() -> new BadRequestException("Invalid supplier"));

        if (!supplier.getActive()) {
    throw new RuntimeException("Inactive supplier");
}


        if (po.getIssuedDate() == null) {
            po.setIssuedDate(LocalDate.now());
        }

        if (po.getStatus() == null) {
            po.setStatus("CREATED");
        }

        store.add(po);
        return po;
    }

    @Override
    public List<PurchaseOrderRecord> getPOsBySupplier(Long supplierId) {
        List<PurchaseOrderRecord> result = new ArrayList<>();
        for (PurchaseOrderRecord po : store) {
            if (supplierId.equals(po.getSupplierId())) {
                result.add(po);
            }
        }
        return result;
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
