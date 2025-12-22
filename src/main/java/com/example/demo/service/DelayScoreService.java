package com.example.demo.service;

import java.util.List;
import com.example.demo.model.DelayScore;

public interface DelayScoreService {

    DelayScore computeDelayScore(Long poId);

    List<DelayScore> getScoresBySupplier(Long supplierId);

    DelayScore getScoreById(Long id);

    List<DelayScore> getAllScores();
}
