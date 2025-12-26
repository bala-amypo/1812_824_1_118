@Service
public class SupplierRiskAlertServiceImpl implements SupplierRiskAlertService {

    private final SupplierRiskAlertRepository repo;

    public SupplierRiskAlertServiceImpl(SupplierRiskAlertRepository repo) {
        this.repo = repo;
    }

    @Override
    public SupplierRiskAlert createAlert(SupplierRiskAlert alert) {

        if (alert.getResolved() == null) {
            alert.setResolved(false); // DEFAULT REQUIRED
        }

        return repo.save(alert);
    }

    @Override
    public SupplierRiskAlert resolveAlert(Long id) {
        SupplierRiskAlert alert = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alert not found"));
        alert.setResolved(true);
        return repo.save(alert);
    }

    @Override
    public List<SupplierRiskAlert> getAlertsBySupplier(Long supplierId) {
        return repo.findBySupplierId(supplierId);
    }

    @Override
    public Optional<SupplierRiskAlert> getAlertById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<SupplierRiskAlert> getAllAlerts() {
        return repo.findAll();
    }
}
