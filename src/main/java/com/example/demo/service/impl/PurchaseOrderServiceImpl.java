package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.PurchaseOrderRecordRepository;
import com.example.demo.repository.SupplierProfileRepository;
import com.example.demo.service.PurchaseOrderService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository poRepo;
    private final SupplierProfileService supplierService;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository poRepo,
                                    SupplierProfileService supplierService) {
        this.poRepo = poRepo;
        this.supplierService = supplierService;
    }

    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrder po) {
        SupplierProfile supplier = supplierService.getSupplierById(po.getSupplierId());

        if (!supplier.getActive()) {
            po.setStatus("REJECTED");
        } else {
            po.setStatus("CREATED");
        }

        return poRepo.save(po);
    }

    @Override
    public List<PurchaseOrder> getPOsBySupplier(Long supplierId) {
        return poRepo.findBySupplierId(supplierId);
    }

    @Override
    public Optional<PurchaseOrder> getPOById(Long id) {
        return poRepo.findById(id);
    }
}

