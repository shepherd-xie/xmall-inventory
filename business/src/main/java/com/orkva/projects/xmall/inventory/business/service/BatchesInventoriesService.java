package com.orkva.projects.xmall.inventory.business.service;

import com.orkva.projects.xmall.inventory.model.entity.enums.InventoryType;
import com.orkva.projects.xmall.inventory.model.entity.pojo.BatchesInventory;
import com.orkva.projects.xmall.inventory.model.repository.BatchesInventoryRepository;
import com.orkva.xmall.common.utils.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * BatchesInventoriesService
 *
 * @author Shepherd Xie
 * @version 2023/8/7
 */
@Service
@Transactional
public class BatchesInventoriesService {

    @Autowired
    private BatchesInventoryRepository batchesInventoryRepository;
    @Autowired
    private BatchesInventoriesChangesLogService batchesInventoriesChangesLogService;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    public BatchesInventory initializeBatchesInventory(Long skuId) {
        BatchesInventory batchesInventory = new BatchesInventory();
        batchesInventory.setSkuId(skuId);
        batchesInventory.setLotNumber(Long.toString(snowflakeIdWorker.nextId()));
        batchesInventory.setTotal(0);
        batchesInventory.setRemaining(0);
        batchesInventory.setAvailable(0);
        batchesInventory.setOutbound(0);
        batchesInventory.setLocked(0);
        batchesInventoryRepository.saveAndFlush(batchesInventory);

        batchesInventoriesChangesLogService.logInventoriesChanges(batchesInventory, 0, InventoryType.INIT);

        return batchesInventory;
    }

    public BatchesInventory purchase(Long skuId, int change) {
        BatchesInventory batchesInventory = initializeBatchesInventory(skuId);
        batchesInventory.setTotal(change);
        batchesInventory.setRemaining(change);
        batchesInventory.setAvailable(change);
        batchesInventoryRepository.saveAndFlush(batchesInventory);

        batchesInventoriesChangesLogService.logInventoriesChanges(batchesInventory, change, InventoryType.PURCHASE);
        return batchesInventory;
    }

    public BatchesInventory locked(String lotNumber, int change) {
        BatchesInventory batchesInventory = batchesInventoryRepository.findByLotNumber(lotNumber).orElseThrow();
        batchesInventory.setLocked(batchesInventory.getLocked() + change);
        batchesInventory.setAvailable(batchesInventory.getAvailable() - change);
        batchesInventoryRepository.saveAndFlush(batchesInventory);

        batchesInventoriesChangesLogService.logInventoriesChanges(batchesInventory, change, InventoryType.LOCKED);
        return batchesInventory;
    }

    public BatchesInventory pickup(String lotNumber, int change) {
        BatchesInventory batchesInventory = batchesInventoryRepository.findByLotNumber(lotNumber).orElseThrow();
        batchesInventory.setLocked(batchesInventory.getLocked() - change);
        batchesInventory.setOutbound(batchesInventory.getOutbound() + change);
        batchesInventory.setRemaining(batchesInventory.getRemaining() - change);
        batchesInventoryRepository.saveAndFlush(batchesInventory);

        batchesInventoriesChangesLogService.logInventoriesChanges(batchesInventory, change, InventoryType.PICKUP);
        return batchesInventory;
    }

    public BatchesInventory recede(String lotNumber, int change) {
        BatchesInventory batchesInventory = batchesInventoryRepository.findByLotNumber(lotNumber).orElseThrow();
        batchesInventory.setAvailable(batchesInventory.getAvailable() + change);
        batchesInventory.setRemaining(batchesInventory.getRemaining() + change);
        batchesInventory.setOutbound(batchesInventory.getOutbound() - change);
        batchesInventoryRepository.saveAndFlush(batchesInventory);

        batchesInventoriesChangesLogService.logInventoriesChanges(batchesInventory, change, InventoryType.RECEDE);
        return batchesInventory;
    }

}
