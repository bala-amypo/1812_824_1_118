package com.example.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.repository.SupplierRiskAlertRepository;
import com.example.demo.service.SupplierRiskAlertService;

@Service
public class SupplierRiskAlertServiceImpl implements SupplierRiskAlertService {

    @Autowired
    private SupplierRiskAlertRepository repo;

    @Override
    public SupplierRiskAlert createAlert(SupplierRiskAlert alert) {
        return repo.save(alert);
    }

    @Override
    public SupplierRiskAlert resolveAlert(Long id) {
        SupplierRiskAlert alert = repo.findById(id).orElseThrow();
        alert.setResolved(true);
        return repo.save(alert);
    }

    @Override
    public List<SupplierRiskAlert> getAlertsBySupplier(Long supplierId) {
        return repo.findBySupplierId(supplierId);
    }

    @Override
    public List<SupplierRiskAlert> getAllAlerts() {
        return repo.findAll();
    }
}
