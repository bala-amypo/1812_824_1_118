package com.example.demo.service.impl;

import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.service.SupplierRiskAlertService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SupplierRiskAlertServiceImpl implements SupplierRiskAlertService {

    private static final Map<Long, SupplierRiskAlert> store = new HashMap<>();
    private static long seq = 1;

    @Override
    public SupplierRiskAlert createAlertForSupplier(
            Long supplierId, String level, String message) {

        SupplierRiskAlert a = new SupplierRiskAlert();
        a.setId(seq++);
        a.setSupplierId(supplierId);
        a.setAlertLevel(level);
        a.setMessage(message);
        a.setResolved(false);

        store.put(a.getId(), a);
        return a;
    }

    @Override
    public SupplierRiskAlert resolveAlert(Long id) {
        SupplierRiskAlert a = store.get(id);
        if (a != null) {
            a.setResolved(true);
        }
        return a;
    }

    @Override
    public List<SupplierRiskAlert> getAlertsBySupplier(Long supplierId) {
        return store.values().stream()
                .filter(a -> supplierId.equals(a.getSupplierId()))
                .toList();
    }

    @Override
    public List<SupplierRiskAlert> getAllAlerts() {
        return new ArrayList<>(store.values());
    }

    @Override
    public SupplierRiskAlert getAlertById(Long id) {
        return store.get(id);
    }
}
