package com.example.demo.service;

import com.example.demo.model.SupplierProfile;

import java.util.List;
import java.util.Optional;

public interface SupplierProfileService {

    SupplierProfile createSupplier(SupplierProfile supplier);

    Optional<SupplierProfile> getSupplierById(Long id);

    Optional<SupplierProfile> getBySupplierId(Long id);

    List<SupplierProfile> getAllSuppliers();

    SupplierProfile updateSupplierStatus(Long id, boolean active);
}
