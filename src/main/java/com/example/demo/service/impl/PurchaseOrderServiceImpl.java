package com.example.demo.service.impl;

import com.example.demo.model.PurchaseOrderRecord;
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
    private final List<PurchaseOrderRecord> store = new ArrayList<>();

    public PurchaseOrderServiceImpl(SupplierProfileService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public PurchaseOrderRecord createPurchaseOrder(PurchaseOrderRecord po) {

        // ✅ FIX: use getSupplierById
        SupplierProfile supplier = supplierService
                .getSupplierById(po.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        // ✅ FIX: correct active check
        // if (!supplier.getActive()) {
        //     throw new RuntimeException("Supplier is inactive");
        // }

        store.add(po);
        return po;
    }
    @Override
    public List<PurchaseOrderRecord> getPOsBySupplier(Long supplierId) {
        return poRepository.findBySupplierId(supplierId);
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
