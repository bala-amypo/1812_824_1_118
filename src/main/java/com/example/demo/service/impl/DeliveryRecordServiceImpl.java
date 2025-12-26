@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private final DeliveryRecordRepository deliveryRepo;
    private final PurchaseOrderRecordRepository poRepo;

    public DeliveryRecordServiceImpl(
            DeliveryRecordRepository deliveryRepo,
            PurchaseOrderRecordRepository poRepo
    ) {
        this.deliveryRepo = deliveryRepo;
        this.poRepo = poRepo;
    }

    @Override
    public DeliveryRecord recordDelivery(DeliveryRecord delivery) {

        poRepo.findById(delivery.getPoId())
                .orElseThrow(() -> new BadRequestException("Invalid PO id"));

        if (delivery.getDeliveredQuantity() < 0) {
            throw new BadRequestException("Quantity must be non-negative");
        }

        return deliveryRepo.save(delivery); // SAVE REQUIRED
    }

    @Override
    public List<DeliveryRecord> getDeliveriesByPO(Long poId) {
        return deliveryRepo.findByPoId(poId);
    }

    @Override
    public Optional<DeliveryRecord> getDeliveryById(Long id) {
        return deliveryRepo.findById(id);
    }

    @Override
    public List<DeliveryRecord> getAllDeliveries() {
        return deliveryRepo.findAll();
    }
}
