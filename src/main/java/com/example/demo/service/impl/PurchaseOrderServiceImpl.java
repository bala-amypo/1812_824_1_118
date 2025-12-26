package com.example.demo.service.impl;

import com.example.demo.model.PurchaseOrder;
import com.example.demo.model.SupplierProfile;
import com.example.demo.service.PurchaseOrderService;
import com.example.demo.service.SupplierProfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final SupplierProfileService supplierService;
    private final List<PurchaseOrder> inMemory = new ArrayList<>();

    public PurchaseOrderServiceImpl(SupplierProfileService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrder po) {
        SupplierProfile supplier = supplierService.getSupplierById(po.getSupplierId());

        if (!supplier.getActive()) {
            po.setStatus("REJECTED");
        } else {
            po.setStatus("CREATED");
        }

        inMemory.add(po);
        return po;
    }

    @Override
    public List<PurchaseOrder> getPOsBySupplier(Long supplierId) {
        List<PurchaseOrder> result = new ArrayList<>();
        for (PurchaseOrder po : inMemory) {
            if (supplierId.equals(po.getSupplierId())) {
                result.add(po);
            }
        }
        return result;
    }

    @Override
    public Optional<PurchaseOrder> getPOById(Long id) {
        return inMemory.stream()
                .filter(po -> id.equals(po.getId()))
                .findFirst();
    }

    @Override
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return inMemory;
    }
}
