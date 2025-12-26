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

    private final DelayScoreRecordRepository delayRepo;
    private final PurchaseOrderRecordRepository poRepo;
    private final DeliveryRecordRepository deliveryRepo;
    private final SupplierProfileRepository supplierRepo;
    private final SupplierRiskAlertService riskAlertService;

    public DelayScoreServiceImpl(
            DelayScoreRecordRepository delayRepo,
            PurchaseOrderRecordRepository poRepo,
            DeliveryRecordRepository deliveryRepo,
            SupplierProfileRepository supplierRepo,
            SupplierRiskAlertService riskAlertService) {

        this.delayRepo = delayRepo;
        this.poRepo = poRepo;
        this.deliveryRepo = deliveryRepo;
        this.supplierRepo = supplierRepo;
        this.riskAlertService = riskAlertService;
    }

    @Override
    public DelayScoreRecord computeDelayScore(Long poId) {

        PurchaseOrderRecord po = poRepo.findById(poId)
                .orElseThrow(() -> new BadRequestException("Invalid PO id"));

        SupplierProfile supplier = supplierRepo.findById(po.getSupplierId())
                .orElseThrow(() -> new BadRequestException("Supplier not found"));

        if (!supplier.getActive()) {
            throw new BadRequestException("Inactive supplier");
        }

        List<DeliveryRecord> deliveries = deliveryRepo.findByPoId(poId);
        if (deliveries.isEmpty()) {
            throw new BadRequestException("No deliveries found");
        }

        DeliveryRecord delivery = deliveries.get(0);

        long delayDays = ChronoUnit.DAYS.between(
                po.getPromisedDeliveryDate(),
                delivery.getActualDeliveryDate()
        );

        if (delayDays < 0) delayDays = 0;

        DelayScoreRecord record = delayRepo.findByPoId(poId)
                .orElse(new DelayScoreRecord());

        record.setPoId(poId);
        record.setSupplierId(po.getSupplierId());
        record.setDelayDays((int) delayDays);

        if (delayDays == 0) {
            record.setDelaySeverity("ON_TIME");
            record.setScore(100.0);
        } else if (delayDays <= 3) {
            record.setDelaySeverity("MINOR");
            record.setScore(90.0);
        } else {
            record.setDelaySeverity("SEVERE");
            record.setScore(60.0);
        }

        return delayRepo.save(record);
    }

    @Override
    public List<DelayScoreRecord> getScoresBySupplier(Long supplierId) {
        return delayRepo.findBySupplierId(supplierId);
    }

    @Override
    public List<DelayScoreRecord> getAllScores() {
        return delayRepo.findAll();
    }
}
