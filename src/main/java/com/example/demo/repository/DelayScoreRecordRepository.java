package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.DelayScoreRecord;
import java.util.List;

public interface DelayScoreRecordRepository extends JpaRepository<DelayScoreRecord,Long>{
    List<DelayScoreRecord> findBySupplierId(Long supplierId);
}