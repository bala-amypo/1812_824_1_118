package com.example.demo.controller;

import com.example.demo.model.SupplierProfile;
import com.example.demo.service.SupplierProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@CrossOrigin(origins = "*")
public class SupplierProfileController {

    private final SupplierProfileService supplierService;

    public SupplierProfileController(SupplierProfileService supplierService) {
        this.supplierService = supplierService;
    }

    // CREATE supplier
    @PostMapping
    public ResponseEntity<SupplierProfile> createSupplier(
            @RequestBody SupplierProfile supplier) {

        SupplierProfile created = supplierService.createSupplier(supplier);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // GET all suppliers
    @GetMapping
    public ResponseEntity<List<SupplierProfile>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    // GET supplier by id
    @GetMapping("/{id}")
    public ResponseEntity<SupplierProfile> getSupplierById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    // UPDATE active status
    @PutMapping("/{id}/status")
    public ResponseEntity<SupplierProfile> updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {

        return ResponseEntity.ok(
                supplierService.updateSupplierStatus(id, active)
        );
    }
}
