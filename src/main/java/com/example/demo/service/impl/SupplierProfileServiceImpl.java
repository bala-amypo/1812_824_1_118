package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.SupplierProfileRepository;
import com.example.demo.service.SupplierProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierProfileServiceImpl implements SupplierProfileService {

    private static final Map<Long, SupplierProfile> store = new HashMap<>();
    private static long seq = 1;

    @Override
    public SupplierProfile createSupplier(SupplierProfile supplier) {
        supplier.setId(seq++);
        supplier.setActive(true);
        store.put(supplier.getId(), supplier);
        return supplier;
    }

    @Override
    public Optional<SupplierProfile> getSupplierById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public SupplierProfile updateSupplierStatus(Long id, boolean active) {
        SupplierProfile s = store.get(id);
        if (s == null) return null;
        s.setActive(active);
        return s;
    }

    @Override
    public List<SupplierProfile> getAllSuppliers() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<SupplierProfile> getBySupplierCode(String code) {
        return store.values().stream()
                .filter(s -> code.equals(s.getSupplierCode()))
                .findFirst();
    }
}
