package com.example.demo.controller;

import com.example.demo.model.DelayScoreRecord;
import com.example.demo.service.DelayScoreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delay-scores")
@Tag(name = "Delay Scores")
public class DelayScoreController {

    private final DelayScoreService service;

    public DelayScoreController(DelayScoreService service) {
        this.service = service;
    }

    @PostMapping("/compute/{poId}")
    public DelayScoreRecord compute(@PathVariable Long poId) {
        return service.computeDelayScore(poId);
    }

    @GetMapping("/supplier/{supplierId}")
    public List<DelayScoreRecord> getBySupplier(@PathVariable Long supplierId) {
        return service.getScoresBySupplier(supplierId);
    }

    @GetMapping("/{id}")
    public DelayScoreRecord getById(@PathVariable Long id) {
        return service.getScoreById(id).orElse(null);
    }

    @GetMapping
    public List<DelayScoreRecord> getAll() {
        return service.getAllScores();
    }
}
