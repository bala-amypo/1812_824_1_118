package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.SupplierRiskAlert;
import java.util.List;

public interface SupplierRiskAlertRepository extends JpaRepository<SupplierRiskAlert,Long>{
    List<SupplierRiskAlert> findBySupplierId(Long supplierId);
}