package com.example.demo.service;

import com.example.demo.model.SupplierRiskAlert;

import java.util.List;
import java.util.Optional;

public interface SupplierRiskAlertService {

    SupplierRiskAlert createAlert(SupplierRiskAlert alert);

    SupplierRiskAlert createHighRiskAlert(Long supplierId);

    SupplierRiskAlert resolveAlert(Long alertId);

    Optional<SupplierRiskAlert> getAlertById(Long id);

    List<SupplierRiskAlert> getAlertsBySupplier(Long supplierId);

    List<SupplierRiskAlert> getAllAlerts();
}
