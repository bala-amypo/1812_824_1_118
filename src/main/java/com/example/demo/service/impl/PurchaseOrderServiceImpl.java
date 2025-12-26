@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRecordRepository poRepo;
    private final SupplierProfileRepository supplierRepo;

    public PurchaseOrderServiceImpl(
            PurchaseOrderRecordRepository poRepo,
            SupplierProfileRepository supplierRepo
    ) {
        this.poRepo = poRepo;
        this.supplierRepo = supplierRepo;
    }

    @Override
    public PurchaseOrderRecord createPurchaseOrder(PurchaseOrderRecord po) {

        SupplierProfile supplier = supplierRepo.findById(po.getSupplierId())
                .orElseThrow(() -> new BadRequestException("Invalid supplierId"));

        if (!supplier.getActive()) {
            throw new BadRequestException("Supplier must be active");
        }

        return poRepo.save(po); // MUST SAVE
    }

    @Override
    public List<PurchaseOrderRecord> getPOsBySupplier(Long supplierId) {
        return poRepo.findBySupplierId(supplierId);
    }

    @Override
    public Optional<PurchaseOrderRecord> getPOById(Long id) {
        return poRepo.findById(id);
    }

    @Override
    public List<PurchaseOrderRecord> getAllPurchaseOrders() {
        return poRepo.findAll();
    }
}
