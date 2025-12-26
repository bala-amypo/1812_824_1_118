package com.example.demo.service;
import org.springframework.stereotype.Service;

import com.example.demo.model.DelayScoreRecord;

import java.util.List;
import java.util.Optional;

public interface DelayScoreService {

    DelayScoreRecord computeDelayScore(Long poId);

    Optional<DelayScoreRecord> getScoreById(Long id);

    List<DelayScoreRecord> getScoresBySupplier(Long supplierId);

    List<DelayScoreRecord> getAllScores();
}
