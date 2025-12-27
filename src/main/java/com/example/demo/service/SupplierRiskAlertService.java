import java.util.List;
import java.util.Optional;

public interface SupplierRiskAlertService {

    SupplierRiskAlert createAlert(SupplierRiskAlert alert);

    List<SupplierRiskAlert> getAlertsBySupplier(Long supplierId);

    List<SupplierRiskAlert> getAllAlerts();

    SupplierRiskAlert resolveAlert(Long id);

    Optional<SupplierRiskAlert> getAlertById(Long id);

    // ✅ ADD THIS
    SupplierRiskAlert createAlertForSupplier(
            Long supplierId,
            String alertLevel,
            String message
    );
}
