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
        throw new BadRequestException("No deliveries");
    }

    LocalDate promised = po.getPromisedDeliveryDate();
    LocalDate actual = deliveries.get(0).getActualDeliveryDate();

    long delayDays = Math.max(0,
            ChronoUnit.DAYS.between(promised, actual));

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
