package com.example.demo.service;

import com.example.demo.model.SupplierRiskAlert;

import java.util.List;
import java.util.Optional;

public interface SupplierRiskAlertService {

    SupplierRiskAlert createAlert(SupplierRiskAlert alert);

    SupplierRiskAlert resolveAlert(Long id);

    List<SupplierRiskAlert> getAlertsBySupplier(Long supplierId);

    Optional<SupplierRiskAlert> getAlertById(Long id);

    List<SupplierRiskAlert> getAllAlerts();
}
