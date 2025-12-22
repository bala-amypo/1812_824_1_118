package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.DeliveryRecord;

public interface DeliveryRecordRepository extends JpaRepository<DeliveryRecord, Long> {

    List<DeliveryRecord> findByPoId(Long poId);
}
