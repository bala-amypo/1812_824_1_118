package com.example.demo.service.impl;

import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.service.PurchaseOrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final List<PurchaseOrderRecord> store = new ArrayList<>();

    // 🔥 EXACT NAME
    @Override
    public PurchaseOrderRecord createPurchaseOrder(PurchaseOrderRecord po) {
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
