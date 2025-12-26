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

    private final DelayScoreRecordRepository delayScoreRecordRepository;
    private final PurchaseOrderRecordRepository purchaseOrderRecordRepository;
    private final DeliveryRecordRepository deliveryRecordRepository;
    private final SupplierProfileRepository supplierProfileRepository;
    private final SupplierRiskAlertService supplierRiskAlertService;

    public DelayScoreServiceImpl(
            DelayScoreRecordRepository delayScoreRecordRepository,
            PurchaseOrderRecordRepository purchaseOrderRecordRepository,
            DeliveryRecordRepository deliveryRecordRepository,
            SupplierProfileRepository supplierProfileRepository,
            SupplierRiskAlertService supplierRiskAlertService) {

        this.delayScoreRecordRepository = delayScoreRecordRepository;
        this.purchaseOrderRecordRepository = purchaseOrderRecordRepository;
        this.deliveryRecordRepository = deliveryRecordRepository;
        this.supplierProfileRepository = supplierProfileRepository;
        this.supplierRiskAlertService = supplierRiskAlertService;
    }

    @Override
    public DelayScoreRecord computeDelayScore(Long poId) {

        PurchaseOrderRecord po = purchaseOrderRecordRepository.findById(poId)
                .orElseThrow(() -> new BadRequestException("Purchase order not found"));

        SupplierProfile supplier = supplierProfileRepository.findById(po.getSupplierId())
                .orElseThrow(() -> new BadRequestException("Invalid supplierId"));

        if (!supplier.getActive()) {
            throw new BadRequestException("Inactive supplier");
        }

        List<DeliveryRecord> deliveries = deliveryRecordRepository.findByPoId(poId);
        if (deliveries.isEmpty()) {
            throw new BadRequestException("No deliveries");
        }

        DeliveryRecord delivery = deliveries.get(0);

        long delayDaysLong = ChronoUnit.DAYS.between(
                po.getPromisedDeliveryDate(),
                delivery.getActualDeliveryDate()
        );

        int delayDays = Math.max(0, (int) delayDaysLong);

        String severity;
        if (delayDays == 0) {
            severity = "ON_TIME";
        } else if (delayDays <= 3) {
            severity = "MINOR";
        } else if (delayDays <= 7) {
            severity = "MODERATE";
        } else {
            severity = "SEVERE";
        }

        double score = Math.max(0, 100 - (delayDays * 5));

        DelayScoreRecord record = new DelayScoreRecord(
                supplier.getId(),
                poId,
                delayDays,
                severity,
                score
        );

        return delayScoreRecordRepository.save(record);
    }

    @Override
    public Optional<DelayScoreRecord> getScoreById(Long id) {
        return delayScoreRecordRepository.findById(id);
    }

    @Override
    public List<DelayScoreRecord> getScoresBySupplier(Long supplierId) {
        return delayScoreRecordRepository.findBySupplierId(supplierId);
    }

    @Override
    public List<DelayScoreRecord> getAllScores() {
        return delayScoreRecordRepository.findAll();
    }
}
