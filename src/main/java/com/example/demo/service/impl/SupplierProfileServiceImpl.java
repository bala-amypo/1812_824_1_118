package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SupplierProfile;
import com.example.demo.service.SupplierProfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierProfileServiceImpl implements SupplierProfileService {

    private final List<SupplierProfile> suppliers = new ArrayList<>();

    @Override
    public SupplierProfile createSupplier(SupplierProfile supplier) {
        suppliers.add(supplier);
        return supplier;
    }

    @Override
    public Optional<SupplierProfile> getSupplierById(Long id) {
        return suppliers.stream()
                .filter(s -> id.equals(s.getId()))
                .findFirst();
    }

    @Override
    public SupplierProfile updateSupplierStatus(Long id, boolean active) {
        SupplierProfile supplier = getSupplierById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
        supplier.setActive(active);
        return supplier;
    }

    @Override
    public Optional<SupplierProfile> getBySupplierId(Long supplierId) {
        return getSupplierById(supplierId);
    }

    @Override
    public Optional<SupplierProfile> getBySupplierCode(String code) {
        return suppliers.stream()
                .filter(s -> code.equals(s.getSupplierCode()))
                .findFirst();
    }

    @Override
    public List<SupplierProfile> getAllSuppliers() {
        return suppliers;
    }
}
