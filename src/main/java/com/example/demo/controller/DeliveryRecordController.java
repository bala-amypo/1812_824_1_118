// package com.example.demo.controller;

// import com.example.demo.model.DeliveryRecord;
// import com.example.demo.service.DeliveryRecordService;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/deliveries")
// @Tag(name = "Deliveries")
// public class DeliveryRecordController {

//     @Autowired
//     private DeliveryRecordService service;

//     @PostMapping
//     public DeliveryRecord record(@RequestBody DeliveryRecord delivery) {
//         return service.recordDelivery(delivery);
//     }

//     @GetMapping("/po/{poId}")
//     public List<DeliveryRecord> getByPO(@PathVariable Long poId) {
//         return service.getDeliveriesByPO(poId);
//     }

//     @GetMapping("/{id}")
//     public DeliveryRecord getById(@PathVariable Long id) {
//         return service.getDeliveryById(id);
//     }

//     @GetMapping
//     public List<DeliveryRecord> getAll() {
//         return service.getAllDeliveries();
//     }
// }
