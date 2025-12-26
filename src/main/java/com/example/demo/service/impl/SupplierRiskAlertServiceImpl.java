package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
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
    public SupplierRiskAlert createAlert(SupplierRiskAlert alert) {
        alert.setResolved(false);
        alerts.add(alert);
        return alert;
    }
    @Override
public SupplierRiskAlert createAlertForSupplier(Long supplierId, String riskLevel, String reason) {
    SupplierRiskAlert alert = new SupplierRiskAlert();
    alert.setSupplierId(supplierId);
    alert.setRiskLevel(riskLevel);
    alert.setResolved(false);
    alerts.add(alert);
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
    return alerts.stream()
            .filter(a -> supplierId.equals(a.getSupplierId()))
            .toList();
}


    @Override
    public List<SupplierRiskAlert> getAllAlerts() {
        return alerts;
    }

    @Override
    public SupplierRiskAlert resolveAlert(Long id) {
        SupplierRiskAlert alert = getAlertById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alert not found"));
        alert.setResolved(true);
        return alert;
    }
}
