package com.example.demo.service.impl;

import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.SupplierProfileRepository;
import com.example.demo.service.SupplierProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierProfileServiceImpl implements SupplierProfileService {

    private final Map<Long, SupplierProfile> store = new HashMap<>();

    @Override
    public SupplierProfile createSupplier(SupplierProfile supplier) {
        if (supplier == null) {
            throw new IllegalArgumentException("Supplier cannot be null");
        }

        if (supplier.getActive() == null) {
            supplier.setActive(true);
        }

        if (supplier.getId() == null) {
            supplier.setId((long) (store.size() + 1));
        }

        store.put(supplier.getId(), supplier);
        return supplier;
    }

    @Override
    public SupplierProfile getSupplierById(Long id) {
        SupplierProfile supplier = store.get(id);
        if (supplier == null) {
            throw new RuntimeException("Supplier not found");
        }
        return supplier;
    }

    @Override
    public SupplierProfile updateSupplierStatus(Long id, boolean active) {
        SupplierProfile supplier = store.get(id);
        if (supplier == null) {
            supplier = new SupplierProfile();
            supplier.setId(id);
            supplier.setActive(active);
            store.put(id, supplier);
        } else {
            supplier.setActive(active);
        }
        return supplier;
    }

    @Override
    public Optional<SupplierProfile> getBySupplierCode(String code) {
        return store.values()
                .stream()
                .filter(s -> code.equals(s.getSupplierCode()))
                .findFirst();
    }

    @Override
    public List<SupplierProfile> getAllSuppliers() {
        return new ArrayList<>(store.values());
    }
}
