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
        return store.computeIfAbsent(id, k -> {
            SupplierProfile s = new SupplierProfile();
            s.setId(k);
            s.setSupplierCode("SUP-" + k);
            s.setStatus("ACTIVE");
            return s;
        });
    }

    @Override
    public SupplierProfile getBySupplierCode(String code) {
        return store.values()
                .stream()
                .filter(s -> code.equals(s.getSupplierCode()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public SupplierProfile updateSupplierStatus(Long id, String status) {
        SupplierProfile s = getSupplierById(id);
        s.setStatus(status);
        return s;
    }

    @Override
    public List<SupplierProfile> getAllSuppliers() {
        return new ArrayList<>(store.values());
    }
}

