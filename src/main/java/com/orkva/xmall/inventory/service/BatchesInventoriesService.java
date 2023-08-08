package com.orkva.xmall.inventory.service;

import com.orkva.xmall.inventory.entity.enums.InventoryType;
import com.orkva.xmall.inventory.entity.pojo.BatchesInventory;
import com.orkva.xmall.inventory.repository.BatchesInventoryRepository;
import com.orkva.xmall.inventory.utils.SnowflakeIdWorker;
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

    public BatchesInventory createBatchesInventory(Long skuId) {
        BatchesInventory batchesInventory = new BatchesInventory();
        batchesInventory.setSkuId(skuId);
        batchesInventory.setLotNumber(Long.toString(snowflakeIdWorker.nextId()));
        batchesInventoryRepository.saveAndFlush(batchesInventory);

        batchesInventoriesChangesLogService.logInventoriesChanges(batchesInventory, 0, InventoryType.INIT);

        return batchesInventory;
    }

    public void purchase(Long skuId, int change) {
        BatchesInventory batchesInventory = createBatchesInventory(skuId);
        batchesInventory.setTotal(change);
        batchesInventory.setRemaining(change);
        batchesInventory.setAvailable(change);
        batchesInventoryRepository.saveAndFlush(batchesInventory);

        batchesInventoriesChangesLogService.logInventoriesChanges(batchesInventory, change, InventoryType.PURCHASE);
    }

    public void locked(String lotNumber, int change) {
        BatchesInventory batchesInventory = batchesInventoryRepository.findByLotNumber(lotNumber).orElseThrow();
        batchesInventory.setLocked(batchesInventory.getLocked() + change);
        batchesInventory.setAvailable(batchesInventory.getAvailable() - change);
        batchesInventoryRepository.saveAndFlush(batchesInventory);

        batchesInventoriesChangesLogService.logInventoriesChanges(batchesInventory, change, InventoryType.LOCKED);
    }

    public void pickup(String lotNumber, int change) {
        BatchesInventory batchesInventory = batchesInventoryRepository.findByLotNumber(lotNumber).orElseThrow();
        batchesInventory.setLocked(batchesInventory.getLocked() - change);
        batchesInventory.setOutbound(batchesInventory.getOutbound() + change);
        batchesInventory.setRemaining(batchesInventory.getRemaining() - change);
        batchesInventoryRepository.saveAndFlush(batchesInventory);

        batchesInventoriesChangesLogService.logInventoriesChanges(batchesInventory, change, InventoryType.PICKUP);
    }

    public void recede(String lotNumber, int change) {
        BatchesInventory batchesInventory = batchesInventoryRepository.findByLotNumber(lotNumber).orElseThrow();
        batchesInventory.setAvailable(batchesInventory.getAvailable() + change);
        batchesInventory.setRemaining(batchesInventory.getRemaining() + change);
        batchesInventory.setOutbound(batchesInventory.getOutbound() - change);
        batchesInventoryRepository.saveAndFlush(batchesInventory);

        batchesInventoriesChangesLogService.logInventoriesChanges(batchesInventory, change, InventoryType.RECEDE);
    }

}
