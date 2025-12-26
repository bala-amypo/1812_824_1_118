package com.example.demo.controller;

import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.service.PurchaseOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {

    private final PurchaseOrderService service;

    public PurchaseOrderController(PurchaseOrderService service) {
        this.service = service;
    }

    @PostMapping
    public PurchaseOrderRecord create(@RequestBody PurchaseOrderRecord po) {
        return service.createPurchaseOrder(po);
    }

    @GetMapping("/{id}")
    public PurchaseOrderRecord getById(@PathVariable Long id) {
        return service.getPOById(id)
                .orElseThrow(() -> new RuntimeException("PO not found"));
    }

    @GetMapping
    public List<PurchaseOrderRecord> getAll() {
        return service.getAllPurchaseOrders();
    }
}
