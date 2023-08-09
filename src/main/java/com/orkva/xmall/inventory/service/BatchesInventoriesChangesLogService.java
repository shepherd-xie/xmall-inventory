package com.orkva.xmall.inventory.service;

import com.orkva.xmall.inventory.entity.enums.InventoryType;
import com.orkva.xmall.inventory.entity.pojo.BatchesInventory;
import com.orkva.xmall.inventory.entity.pojo.BatchesInventoryChangeLog;
import com.orkva.xmall.inventory.repository.BatchesInventoryChangeLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

/**
 * BatchesInventoriesChangesLogService
 *
 * @author Shepherd Xie
 * @version 2023/8/8
 */
@Service
@Slf4j
@Transactional
public class BatchesInventoriesChangesLogService {

    @Autowired
    private BatchesInventoryChangeLogRepository batchesInventoryChangeLogRepository;


    public void logInventoriesChanges(BatchesInventory changedBatchesInventory, int change, InventoryType inventoryType) {
        BatchesInventoryChangeLog batchesInventoryChangeLog = new BatchesInventoryChangeLog();
        batchesInventoryChangeLog.setLotNumber(changedBatchesInventory.getLotNumber());
        batchesInventoryChangeLog.setSkuId(changedBatchesInventory.getSkuId());
        batchesInventoryChangeLog.setInventoryType(inventoryType);
        batchesInventoryChangeLog.setTotal(changedBatchesInventory.getTotal());
        batchesInventoryChangeLog.setTotalChanges(0);
        batchesInventoryChangeLog.setRemaining(changedBatchesInventory.getRemaining());
        batchesInventoryChangeLog.setRemainingChanges(0);
        batchesInventoryChangeLog.setOutbound(changedBatchesInventory.getOutbound());
        batchesInventoryChangeLog.setOutboundChanges(0);
        batchesInventoryChangeLog.setAvailable(changedBatchesInventory.getAvailable());
        batchesInventoryChangeLog.setAvailableChanges(0);
        batchesInventoryChangeLog.setLocked(changedBatchesInventory.getLocked());
        batchesInventoryChangeLog.setLockedChanges(0);
        batchesInventoryChangeLog.setCreatedDate(Instant.now());
        batchesInventoryChangeLog.setUpdatedDate(Instant.now());

        switch (inventoryType) {
            case LOCKED -> {
                batchesInventoryChangeLog.setLockedChanges(change);
                batchesInventoryChangeLog.setAvailableChanges(-change);
            }
            case PURCHASE -> {
                batchesInventoryChangeLog.setTotalChanges(change);
                batchesInventoryChangeLog.setRemainingChanges(change);
                batchesInventoryChangeLog.setAvailableChanges(change);
            }
            case PICKUP -> {
                batchesInventoryChangeLog.setRemainingChanges(-change);
                batchesInventoryChangeLog.setOutboundChanges(change);
                batchesInventoryChangeLog.setLockedChanges(-change);
            }
            case RECEDE -> {
                batchesInventoryChangeLog.setRemainingChanges(change);
                batchesInventoryChangeLog.setAvailableChanges(change);
                batchesInventoryChangeLog.setOutboundChanges(-change);
            }
        }

        batchesInventoryChangeLogRepository.save(batchesInventoryChangeLog);
    }

}
