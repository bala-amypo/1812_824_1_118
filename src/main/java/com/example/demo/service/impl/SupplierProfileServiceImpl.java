package com.example.demo.service.impl;

import com.example.demo.model.SupplierProfile;
import com.example.demo.service.SupplierProfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierProfileServiceImpl implements SupplierProfileService {

    private final List<SupplierProfile> store = new ArrayList<>();

    @Override
    public SupplierProfile createSupplier(SupplierProfile supplier) {
        store.add(supplier);
        return supplier;
    }

    @Override
    public Optional<SupplierProfile> getSupplierById(Long id) {
        return store.stream()
                .filter(s -> id.equals(s.getId()))
                .findFirst();
    }

    @Override
    public Optional<SupplierProfile> getBySupplierCode(String code) {
        return store.stream()
                .filter(s -> code.equals(s.getSupplierCode()))
                .findFirst();
    }

    @Override
    public List<SupplierProfile> getAllSuppliers() {
        return store;
    }

    @Override
    public SupplierProfile updateSupplierStatus(Long id, boolean active) {
        SupplierProfile supplier = getSupplierById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        supplier.setActive(active);
        return supplier;
    }
}
