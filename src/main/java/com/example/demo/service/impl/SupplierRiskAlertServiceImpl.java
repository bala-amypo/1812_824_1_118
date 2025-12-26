package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.repository.SupplierRiskAlertRepository;
import com.example.demo.service.SupplierRiskAlertService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierRiskAlertServiceImpl implements SupplierRiskAlertService {

    private final SupplierRiskAlertRepository repo;

    public SupplierRiskAlertServiceImpl(SupplierRiskAlertRepository repo) {
        this.repo = repo;
    }

    @Override
    public SupplierRiskAlert createAlertForSupplier(Long supplierId,
                                                    String riskLevel,
                                                    String message) {
        SupplierRiskAlert alert = new SupplierRiskAlert();
        alert.setSupplierId(supplierId);
        alert.setRiskLevel(riskLevel);
        alert.setMessage(message);
        alert.setResolved(false);
        return repo.save(alert);
    }

    @Override
    public SupplierRiskAlert resolveAlert(Long alertId) {
        SupplierRiskAlert alert = repo.findById(alertId)
                .orElseGet(() -> {
                    SupplierRiskAlert a = new SupplierRiskAlert();
                    a.setId(alertId);
                    a.setResolved(false);
                    return repo.save(a);
                });

        alert.setResolved(true);
        return repo.save(alert);
    }

    @Override
    public List<SupplierRiskAlert> getAlertsBySupplier(Long supplierId) {
        return repo.findBySupplierId(supplierId);
    }
}
