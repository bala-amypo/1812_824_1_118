// package com.example.demo.repository;

// import org.springframework.data.jpa.repository.JpaRepository;
// import com.example.demo.model.PurchaseOrderRecord;

// public interface PurchaseOrderRecordRepository extends JpaRepository<PurchaseOrderRecord,Long>{
    
// }

package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.PurchaseOrderRecord;

public interface PurchaseOrderRecordRepository extends JpaRepository<PurchaseOrderRecord, Long> {
    List<PurchaseOrderRecord> findBySupplierId(Long supplierId);
}
