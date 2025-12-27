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
        SupplierProfile supplier;
        try {
            supplier = supplierService.getSupplierById(po.getSupplierId());
        } catch (Exception e) {
            throw new BadRequestException("Invalid supplierId");
        }

        if (!supplier.getActive()) {
            throw new BadRequestException("Supplier must be active");
        }

        po.setId(seq++);
        if (po.getIssuedDate() == null) {
            po.setIssuedDate(LocalDate.now());
        }

        store.put(po.getId(), po);
        return po;
    }

    @Override
    public List<PurchaseOrderRecord> getPOsBySupplier(Long supplierId) {
        List<PurchaseOrderRecord> list = new ArrayList<>();
        for (PurchaseOrderRecord p : store.values()) {
            if (supplierId.equals(p.getSupplierId())) {
                list.add(p);
            }
        }
        return list;
    }

    @Override
    public Optional<PurchaseOrderRecord> getPOById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<PurchaseOrderRecord> getAllPurchaseOrders() {
        return new ArrayList<>(store.values());
    }
}
