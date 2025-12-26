package com.example.demo.service;

import com.example.demo.model.SupplierProfile;

import java.util.List;
import java.util.Optional;

public interface SupplierProfileService {

    SupplierProfile createSupplier(SupplierProfile supplier);

    Optional<SupplierProfile> getSupplierById(Long id);

    SupplierProfile updateSupplierStatus(Long id, boolean active);

    Optional<SupplierProfile> getBySupplierId(Long supplierId);

    Optional<SupplierProfile> getBySupplierCode(String code);

    List<SupplierProfile> getAllSuppliers();
}
