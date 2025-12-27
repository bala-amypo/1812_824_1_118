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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class DelayScoreServiceImpl implements DelayScoreService {

    private final DelayScoreRecordRepository delayRepo;
    private final PurchaseOrderRecordRepository poRepo;
    private final DeliveryRecordRepository deliveryRepo;
    private final SupplierProfileRepository supplierRepo;
    private final SupplierRiskAlertService alertService;

    // EXACT CONSTRUCTOR ORDER REQUIRED BY TESTS
    public DelayScoreServiceImpl(
            DelayScoreRecordRepository delayRepo,
            PurchaseOrderRecordRepository poRepo,
            DeliveryRecordRepository deliveryRepo,
            SupplierProfileRepository supplierRepo,
            SupplierRiskAlertService alertService
    ) {
        this.delayRepo = delayRepo;
        this.poRepo = poRepo;
        this.deliveryRepo = deliveryRepo;
        this.supplierRepo = supplierRepo;
        this.alertService = alertService;
    }

    @Override
    public DelayScoreRecord computeDelayScore(Long poId) {

        PurchaseOrderRecord po = poRepo.findById(poId)
                .orElseThrow(() ->
                        new BadRequestException("Invalid PO id"));

        SupplierProfile supplier = supplierRepo.findById(po.getSupplierId())
                .orElseThrow(() ->
                        new BadRequestException("Supplier not found"));

        if (!supplier.getActive()) {
            throw new BadRequestException("Inactive supplier");
        }

        List<DeliveryRecord> deliveries =
                deliveryRepo.findByPoId(poId);

        if (deliveries.isEmpty()) {
            throw new BadRequestException("No deliveries");
        }

        LocalDate promised = po.getPromisedDeliveryDate();
        LocalDate actual = deliveries.get(0).getActualDeliveryDate();

        long delayDays =
                Math.max(0, ChronoUnit.DAYS.between(promised, actual));

        String severity;
        double score;

        if (delayDays == 0) {
            severity = "ON_TIME";
            score = 100.0;
        } else if (delayDays <= 3) {
            severity = "MINOR";
            score = 90.0;
        } else if (delayDays <= 7) {
            severity = "MODERATE";
            score = 70.0;
        } else {
            severity = "SEVERE";
            score = 40.0;
            alertService.createAlertForSupplier(
                    supplier.getId(),
                    "HIGH",
                    "Severe delivery delay"
            );
        }

        DelayScoreRecord record = new DelayScoreRecord();
        record.setPoId(poId);
        record.setSupplierId(supplier.getId());
        record.setDelayDays((int) delayDays);
        record.setDelaySeverity(severity);
        record.setScore(score);

        return delayRepo.save(record);
    }

    @Override
    public List<DelayScoreRecord> getScoresBySupplier(Long supplierId) {
        return delayRepo.findBySupplierId(supplierId);
    }

    @Override
    public Optional<DelayScoreRecord> getScoreById(Long id) {
        return delayRepo.findById(id);
    }

    @Override
    public List<DelayScoreRecord> getAllScores() {
        return delayRepo.findAll();
    }
}
