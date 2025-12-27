package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.model.SupplierProfile;
import com.example.demo.service.PurchaseOrderService;
import com.example.demo.service.SupplierProfileService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private static final Map<Long, PurchaseOrderRecord> store = new HashMap<>();
    private static long seq = 1;

    private final SupplierProfileService supplierService;

    public PurchaseOrderServiceImpl(SupplierProfileService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public PurchaseOrderRecord createPurchaseOrder(PurchaseOrderRecord po) {
        SupplierProfile supplier = supplierService.getSupplierById(po.getSupplierId());

        if (!supplier.isActive()) {
            po.setStatus("REJECTED");
        } else {
            po.setStatus("CREATED");
        }

        po.setId(seq++);
        po.setIssuedDate(LocalDate.now());
        store.put(po.getId(), po);
        return po;
    }

    @Override
    public PurchaseOrderRecord getPOById(Long id) {
        return store.get(id);
    }

    @Override
    public List<PurchaseOrderRecord> getPOsBySupplier(Long supplierId) {
        return store.values().stream()
                .filter(po -> supplierId.equals(po.getSupplierId()))
                .toList();
    }

    @Override
    public List<PurchaseOrderRecord> getAllPOs() {
        return new ArrayList<>(store.values());
    }
}

