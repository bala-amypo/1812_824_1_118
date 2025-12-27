package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.DeliveryRecord;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.repository.PurchaseOrderRecordRepository;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private static final Map<Long, DeliveryRecord> store = new HashMap<>();
    private static long seq = 1;

    @Autowired
    private PurchaseOrderService poService;

    @Override
    public DeliveryRecord recordDelivery(DeliveryRecord record) {

        if (poService.getPOById(record.getPurchaseOrderId()).isEmpty()) {
            return null;
        }

        record.setId(seq++);
        store.put(record.getId(), record);
        return record;
    }

    @Override
    public List<DeliveryRecord> getDeliveriesByPO(Long poId) {
        return store.values().stream()
                .filter(d -> poId.equals(d.getPurchaseOrderId()))
                .toList();
    }

    @Override
    public Optional<DeliveryRecord> getDeliveryById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}
