package com.example.demo.service.impl;

import com.example.demo.model.DelayScoreRecord;
import com.example.demo.repository.DelayScoreRecordRepository;
import com.example.demo.service.DelayScoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DelayScoreServiceImpl implements DelayScoreService {

    private final DelayScoreRecordRepository repo;

    public DelayScoreServiceImpl(DelayScoreRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public DelayScoreRecord computeDelayScore(Long poId) {
        throw new UnsupportedOperationException("Mock logic");
    }

    @Override
    public Optional<DelayScoreRecord> getScoreById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<DelayScoreRecord> getScoresBySupplier(Long supplierId) {
        return repo.findBySupplierId(supplierId);
    }

    @Override
    public List<DelayScoreRecord> getAllScores() {
        return repo.findAll();
    }
}
