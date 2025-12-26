package com.example.demo.service;

import com.example.demo.model.SupplierProfile;

import java.util.List;
import java.util.Optional;

public interface SupplierProfileService {

    Optional<SupplierProfile> getBySupplierId(Long supplierId);

    Optional<SupplierProfile> getBySupplierCode(String code);

    List<SupplierProfile> getAllSuppliers();
}
