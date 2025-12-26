package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SupplierProfile;
import com.example.demo.service.SupplierProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierProfileController {

    private final SupplierProfileService service;

    public SupplierProfileController(SupplierProfileService service) {
        this.service = service;
    }

    @PostMapping
    public SupplierProfile create(@RequestBody SupplierProfile supplier) {
        return service.createSupplier(supplier);
    }

    @GetMapping("/{id}")
public SupplierProfile getById(@PathVariable Long id) {
    return service.getSupplierById(id)
            .orElseThrow(() -> new RuntimeException("Supplier not found"));
}


    @PatchMapping("/{id}/status")
    public SupplierProfile updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {

        return service.updateSupplierStatus(id, active);
    }
}
