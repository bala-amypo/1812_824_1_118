package com.example.demo.repository;

import com.example.demo.model.SupplierProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupplierProfileRepository
        extends JpaRepository<SupplierProfile, Long> {

    // Used in lookup & mock tests
    Optional<SupplierProfile> findBySupplierCode(String supplierCode);

    // Used in criteria tests
    List<SupplierProfile> findByActiveTrue();

    List<SupplierProfile> findByEmailIsNotNull();

    List<SupplierProfile> findBySupplierCodeContaining(String supplierCodePart);

    List<SupplierProfile> findAll();

}
