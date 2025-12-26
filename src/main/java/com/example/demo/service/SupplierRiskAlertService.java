package com.example.demo.service;

import com.example.demo.model.SupplierRiskAlert;
import java.util.List;
import java.util.Optional;

public interface SupplierRiskAlertService {

    SupplierRiskAlert createAlert(SupplierRiskAlert alert);

    SupplierRiskAlert createAlertForSupplier(
            Long supplierId,
            String level,
            String message
    );

    SupplierRiskAlert resolveAlert(Long id);

    List<SupplierRiskAlert> getAlertsBySupplier(Long supplierId);

    List<SupplierRiskAlert> getAllAlerts();

    Optional<SupplierRiskAlert> getAlertById(Long id);
}
