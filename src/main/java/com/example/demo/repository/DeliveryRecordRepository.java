package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.DeliveryRecord;
import java.util.List;

public interface DeliveryRecordRepository extends JpaRepository<DeliveryRecord,Long>{
    List<DeliveryRecord> getDeliveriesByPO(Long poId);
    
}