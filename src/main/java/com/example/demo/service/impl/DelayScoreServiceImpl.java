package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.DelayScoreRecord;
import com.example.demo.model.DeliveryRecord;
import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.DelayScoreRecordRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.repository.PurchaseOrderRecordRepository;
import com.example.demo.repository.SupplierProfileRepository;
import com.example.demo.service.DelayScoreService;
import com.example.demo.service.SupplierRiskAlertService;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class DelayScoreServiceImpl implements DelayScoreService {

    private final DelayScoreRecordRepository scoreRepo;
    private final PurchaseOrderRecordRepository poRepo;
    private final DeliveryRecordRepository deliveryRepo;
    private final SupplierProfileRepository supplierRepo;
    private final SupplierRiskAlertService alertService;

    public DelayScoreServiceImpl(
            DelayScoreRecordRepository scoreRepo,
            PurchaseOrderRecordRepository poRepo,
            DeliveryRecordRepository deliveryRepo,
            SupplierProfileRepository supplierRepo,
            SupplierRiskAlertService alertService) {
        this.scoreRepo = scoreRepo;
        this.poRepo = poRepo;
        this.deliveryRepo = deliveryRepo;
        this.supplierRepo = supplierRepo;
        this.alertService = alertService;
    }

    @Override
    public DelayScoreRecord computeDelayScore(Long poId) {
        PurchaseOrderRecord po = poRepo.findById(poId)
                .orElseThrow(() -> new BadRequestException("Invalid PO"));

        SupplierProfile supplier = supplierRepo.findById(po.getSupplierId())
                .orElseThrow(() -> new BadRequestException("Supplier not found"));

        if (!Boolean.TRUE.equals(supplier.getActive())) {
            throw new BadRequestException("Inactive supplier");
        }

        List<DeliveryRecord> deliveries = deliveryRepo.findByPoId(poId);
        if (deliveries.isEmpty()) {
            throw new BadRequestException("No deliveries");
        }

        LocalDate actual = deliveries.get(0).getActualDeliveryDate();
        long delayDays = Math.max(0,
                ChronoUnit.DAYS.between(po.getPromisedDeliveryDate(), actual));

        DelayScoreRecord score = new DelayScoreRecord();
        score.setPoId(poId);
        score.setSupplierId(po.getSupplierId());
        score.setDelayDays((int) delayDays);

        if (delayDays == 0) {
            score.setDelaySeverity("ON_TIME");
            score.setScore(100.0);
        } else if (delayDays <= 3) {
            score.setDelaySeverity("MINOR");
            score.setScore(90.0);
        } else {
            score.setDelaySeverity("SEVERE");
            score.setScore(60.0);

            SupplierRiskAlert alert = new SupplierRiskAlert();
            alert.setSupplierId(po.getSupplierId());
            alert.setAlertLevel("HIGH");
            alertService.createAlert(alert);
        }

        return scoreRepo.save(score);
    }

    @Override
    public Optional<DelayScoreRecord> getScoreById(Long id) {
        return scoreRepo.findById(id);
    }

    @Override
    public List<DelayScoreRecord> getScoresBySupplier(Long supplierId) {
        return scoreRepo.findBySupplierId(supplierId);
    }

    @Override
    public List<DelayScoreRecord> getAllScores() {
        return scoreRepo.findAll();
    }
}
