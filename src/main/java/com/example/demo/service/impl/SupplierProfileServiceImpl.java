package com.example.demo.service.impl;

import com.example.demo.model.SupplierProfile;
import com.example.demo.service.SupplierProfileService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SupplierProfileServiceImpl implements SupplierProfileService {

    private static final Map<Long, SupplierProfile> store = new HashMap<>();
    private static long seq = 1;

    @Override
    public SupplierProfile createSupplier(SupplierProfile supplier) {
        supplier.setId(seq++);
        store.put(supplier.getId(), supplier);
        return supplier;
    }

    @Override
    public SupplierProfile getSupplierById(Long id) {
        SupplierProfile s = store.get(id);
        if (s == null) throw new RuntimeException("Supplier not found");
        return s;
    }

    @Override
    public List<SupplierProfile> getAllSuppliers() {
        return new ArrayList<>(store.values());
    }

    @Override
    public SupplierProfile updateSupplierStatus(Long id, boolean active) {
        SupplierProfile s = getSupplierById(id);
        s.setActive(active);
        return s;
    }

    @Override
    public Optional<SupplierProfile> getBySupplierCode(String code) {
        return store.values().stream()
                .filter(s -> code.equals(s.getSupplierCode()))
                .findFirst();
    }
}
