package com.orkva.projects.xmall.inventory.business.service;

import com.orkva.projects.xmall.inventory.common.entity.InventoryType;
import com.orkva.projects.xmall.inventory.model.entity.pojo.BatchesInventory;
import com.orkva.projects.xmall.inventory.model.entity.pojo.BatchesInventoryChangeLog;
import com.orkva.projects.xmall.inventory.model.repository.BatchesInventoryChangeLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        batchesInventoryChangeLog.setTotalChanges(NumberUtils.INTEGER_ZERO);
        batchesInventoryChangeLog.setRemaining(changedBatchesInventory.getRemaining());
        batchesInventoryChangeLog.setRemainingChanges(NumberUtils.INTEGER_ZERO);
        batchesInventoryChangeLog.setOutbound(changedBatchesInventory.getOutbound());
        batchesInventoryChangeLog.setOutboundChanges(NumberUtils.INTEGER_ZERO);
        batchesInventoryChangeLog.setAvailable(changedBatchesInventory.getAvailable());
        batchesInventoryChangeLog.setAvailableChanges(NumberUtils.INTEGER_ZERO);
        batchesInventoryChangeLog.setLocked(changedBatchesInventory.getLocked());
        batchesInventoryChangeLog.setLockedChanges(NumberUtils.INTEGER_ZERO);

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
