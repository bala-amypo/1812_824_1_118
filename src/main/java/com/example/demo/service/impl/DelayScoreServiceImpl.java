package com.example.demo.service.impl;

import com.example.demo.model.DelayScoreRecord;
import com.example.demo.repository.DelayScoreRecordRepository;
import com.example.demo.service.DelayScoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DelayScoreServiceImpl implements DelayScoreService {

    private final DelayScoreRecordRepository scoreRepository;

    public DelayScoreServiceImpl(DelayScoreRecordRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    @Override
    public Optional<DelayScoreRecord> getScoreById(Long id) {
        return scoreRepository.findById(id);
    }

    @Override
    public List<DelayScoreRecord> getAllScores() {
        return scoreRepository.findAll();
    }
}
