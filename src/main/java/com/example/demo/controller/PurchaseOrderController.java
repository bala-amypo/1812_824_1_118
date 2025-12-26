package com.example.demo.controller;

import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.service.PurchaseOrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {

    private final PurchaseOrderService service;

    public PurchaseOrderController(PurchaseOrderService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public PurchaseOrderRecord getById(@PathVariable Long id) {
        return service.getPOById(id)
                .orElseThrow(() -> new RuntimeException("PO not found"));
    }
}
