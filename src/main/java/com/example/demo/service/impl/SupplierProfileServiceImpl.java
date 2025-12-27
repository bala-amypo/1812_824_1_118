package com.example.demo.service.impl;

import com.example.demo.model.SupplierProfile;
import com.example.demo.service.SupplierProfileService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SupplierProfileServiceImpl implements SupplierProfileService {

    private final SupplierProfileRepository repo;

    public SupplierProfileServiceImpl(SupplierProfileRepository repo) {
        this.repo = repo;
    }

    @Override
    public SupplierProfile getSupplierById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
    }

    @Override
    public SupplierProfile createSupplier(SupplierProfile supplier) {
        if (supplier.getActive() == null) {
            supplier.setActive(true); // default
        }
        return repo.save(supplier);
    }

    @Override
    public List<SupplierProfile> getAllSuppliers() {
        return repo.findAll();
    }

    @Override
    public SupplierProfile updateSupplierStatus(Long id, boolean active) {
        SupplierProfile supplier = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        supplier.setActive(active);
        return repo.save(supplier);
    }

    @Override
    public Optional<SupplierProfile> getBySupplierCode(String code) {
        return repo.findBySupplierCode(code);
    }
}
