package com.example.demo.service.impl;

import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.service.SupplierRiskAlertService;
import org.springframework.stereotype.Service;

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
    public SupplierRiskAlert createAlert(SupplierRiskAlert alert) {
        alert.setId(seq++);
        alert.setResolved(false);
        store.put(alert.getId(), alert);
        return alert;
    }

    @Override
    public SupplierRiskAlert createAlertForSupplier(
            Long supplierId, String level, String message) {

        SupplierRiskAlert alert = new SupplierRiskAlert();
        alert.setSupplierId(supplierId);
        alert.setAlertLevel(level);
        alert.setMessage(message);
        return createAlert(alert);
    }

    @Override
    public Optional<SupplierRiskAlert> getAlertById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<SupplierRiskAlert> getAlertsBySupplier(Long supplierId) {
        return store.values().stream()
                .filter(a -> supplierId.equals(a.getSupplierId()))
                .collect(Collectors.toList());
    }

    @Override
    public SupplierRiskAlert resolveAlert(Long id) {
        SupplierRiskAlert a = store.get(id);
        if (a == null) return null;
        a.setResolved(true);
        return a;
    }
    
    @Override
    public List<SupplierRiskAlert> getAllAlerts() {
        return new ArrayList<>(store.values());
    }

}
