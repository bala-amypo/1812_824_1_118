package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.SupplierRiskAlert;

public interface SupplierRiskAlertRepository extends JpaRepository<SupplierRiskAlert,Long>{
    
}