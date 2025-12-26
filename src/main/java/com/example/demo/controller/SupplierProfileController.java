package com.example.demo.controller;

import com.example.demo.model.SupplierProfile;
import com.example.demo.service.SupplierProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@Tag(name = "Suppliers")
public class SupplierProfileController {

    private final SupplierProfileService service;

    public SupplierProfileController(SupplierProfileService service) {
        this.service = service;
    }

    @PostMapping
    public SupplierProfile createSupplier(@RequestBody SupplierProfile supplier) {
        return service.createSupplier(supplier);
    }

    @GetMapping("/{id}")
    public SupplierProfile getSupplier(@PathVariable Long id) {
        return service.getSupplierById(id);
    }

    @GetMapping
    public List<SupplierProfile> getAllSuppliers() {
        return service.getAllSuppliers();
    }

    @PutMapping("/{id}/status")
    public SupplierProfile updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active
    ) {
        return service.updateSupplierStatus(id, active);
    }

    @GetMapping("/lookup/{supplierCode}")
    public SupplierProfile lookupByCode(@PathVariable String supplierCode) {
        return service.getBySupplierCode(supplierCode).orElse(null);
    }
}
