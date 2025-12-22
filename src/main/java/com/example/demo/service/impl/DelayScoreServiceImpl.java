package com.example.demo.service.impl;

import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.DelayScoreRecord;
import com.example.demo.model.DeliveryRecord;
import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.DelayScoreRecordRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.repository.PurchaseOrderRepository;
import com.example.demo.repository.SupplierProfileRepository;
import com.example.demo.service.DelayScoreService;

@Service
public class DelayScoreServiceImpl implements DelayScoreService {

    @Autowired
    private PurchaseOrderRepository poRepo;

    @Autowired
    private DeliveryRecordRepository deliveryRepo;

    @Autowired
    private SupplierProfileRepository supplierRepo;

    @Autowired
    private DelayScoreRepository scoreRepo;

    @Override
    public DelayScore computeDelayScore(Long poId) {

        PurchaseOrderRecord po = poRepo.findById(poId)
                .orElseThrow(() -> new RuntimeException("PO not found"));

        List<DeliveryRecord> deliveries = deliveryRepo.findByPoId(poId);
        if (deliveries.isEmpty()) {
            throw new RuntimeException("No deliveries");
        }

        SupplierProfile supplier = supplierRepo.findById(po.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        if (!supplier.isActive()) {
            throw new RuntimeException("Inactive supplier");
        }

        long delayDays = ChronoUnit.DAYS.between(
                po.getExpectedDate(),
                deliveries.get(0).getDeliveredDate()
        );

        DelayScore score = new DelayScore();
        score.setPoId(poId);
        score.setSupplierId(supplier.getId());
        score.setDelayDays(delayDays);

        return scoreRepo.save(score);
    }

    @Override
    public List<DelayScore> getScoresBySupplier(Long supplierId) {
        return scoreRepo.findBySupplierId(supplierId);
    }

    @Override
    public DelayScore getScoreById(Long id) {
        return scoreRepo.findById(id).orElse(null);
    }

    @Override
    public List<DelayScore> getAllScores() {
        return scoreRepo.findAll();
    }
}
