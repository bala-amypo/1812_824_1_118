package com.example.demo.service.impl;

import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.service.SupplierRiskAlertService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierRiskAlertServiceImpl implements SupplierRiskAlertService {

    private final List<SupplierRiskAlert> alerts = new ArrayList<>();

    @Override
    public SupplierRiskAlert createAlertForSupplier(Long supplierId,
                                                    String riskLevel,
                                                    String message) {
        SupplierRiskAlert alert = new SupplierRiskAlert();
        alert.setSupplierId(supplierId);
        alert.setRiskLevel(riskLevel);
        alert.setMessage(message);
        alert.setResolved(false);
        alerts.add(alert);
        return alert;
    }

    @Override
    public SupplierRiskAlert resolveAlert(Long alertId) {
        SupplierRiskAlert alert = getAlertById(alertId).orElse(null);
        if (alert != null) {
            alert.setResolved(true);
        }
        return alert;
    }

    @Override
    public Optional<SupplierRiskAlert> getAlertById(Long id) {
        return alerts.stream()
                .filter(a -> id.equals(a.getId()))
                .findFirst();
    }

    @Override
    public List<SupplierRiskAlert> getAlertsBySupplier(Long supplierId) {
        List<SupplierRiskAlert> result = new ArrayList<>();
        for (SupplierRiskAlert a : alerts) {
            if (supplierId.equals(a.getSupplierId())) {
                result.add(a);
            }
        }
        return result;
    }
}
