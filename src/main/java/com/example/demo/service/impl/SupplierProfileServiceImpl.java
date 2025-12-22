package com.example.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.SupplierProfileRepository;
import com.example.demo.service.SupplierProfileService;

@Service
public class SupplierProfileServiceImpl implements SupplierProfileService {
    @Autowired
    private SupplierProfileRepository repo;

    @Override
    public SupplierProfile createSupplier(SupplierProfile supplier) {
        return repo.save(supplier);
    }

    @Override
    public SupplierProfile getSupplierById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
    }

    @Override
    public SupplierProfile getBySupplierCode(String supplierCode) {
        return repo.findBySupplierCode(supplierCode);
    }

    @Override
    public List<SupplierProfile> getAllSuppliers() {
        return repo.findAll();
    }

    @Override
    public SupplierProfile updateSupplierStatus(Long id, boolean active) {
        SupplierProfile supplier = getSupplierById(id);
        supplier.setActive(active);
        return repo.save(supplier);
    }
}
