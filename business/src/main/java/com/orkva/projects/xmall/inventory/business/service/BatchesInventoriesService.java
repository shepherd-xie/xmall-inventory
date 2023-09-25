package com.orkva.projects.xmall.inventory.business.service;

import com.orkva.projects.xmall.inventory.business.exception.BusinessException;
import com.orkva.projects.xmall.inventory.common.entity.InventoryType;
import com.orkva.projects.xmall.inventory.model.entity.pojo.BatchesInventory;
import com.orkva.projects.xmall.inventory.model.repository.BatchesInventoryRepository;
import com.orkva.xmall.common.utils.SnowflakeIdWorker;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Predicate;

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
        batchesInventory.setTotal(NumberUtils.INTEGER_ZERO);
        batchesInventory.setRemaining(NumberUtils.INTEGER_ZERO);
        batchesInventory.setAvailable(NumberUtils.INTEGER_ZERO);
        batchesInventory.setOutbound(NumberUtils.INTEGER_ZERO);
        batchesInventory.setLocked(NumberUtils.INTEGER_ZERO);
        batchesInventoryRepository.saveAndFlush(batchesInventory);

        batchesInventoriesChangesLogService
                .logInventoriesChanges(batchesInventory, NumberUtils.INTEGER_ZERO, InventoryType.INIT);

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
        checkInventory(batchesInventory);
        batchesInventoryRepository.saveAndFlush(batchesInventory);

        batchesInventoriesChangesLogService.logInventoriesChanges(batchesInventory, change, InventoryType.LOCKED);
        return batchesInventory;
    }

    public BatchesInventory pickup(String lotNumber, int change) {
        BatchesInventory batchesInventory = batchesInventoryRepository.findByLotNumber(lotNumber).orElseThrow();
        batchesInventory.setLocked(batchesInventory.getLocked() - change);
        batchesInventory.setOutbound(batchesInventory.getOutbound() + change);
        batchesInventory.setRemaining(batchesInventory.getRemaining() - change);
        checkInventory(batchesInventory);
        batchesInventoryRepository.saveAndFlush(batchesInventory);

        batchesInventoriesChangesLogService.logInventoriesChanges(batchesInventory, change, InventoryType.PICKUP);
        return batchesInventory;
    }

    public BatchesInventory recede(String lotNumber, int change) {
        BatchesInventory batchesInventory = batchesInventoryRepository.findByLotNumber(lotNumber).orElseThrow();
        batchesInventory.setAvailable(batchesInventory.getAvailable() + change);
        batchesInventory.setRemaining(batchesInventory.getRemaining() + change);
        batchesInventory.setOutbound(batchesInventory.getOutbound() - change);
        checkInventory(batchesInventory);
        batchesInventoryRepository.saveAndFlush(batchesInventory);

        batchesInventoriesChangesLogService.logInventoriesChanges(batchesInventory, change, InventoryType.RECEDE);
        return batchesInventory;
    }

    private Predicate<BatchesInventory> availablePredicate = i ->
            i.getAvailable() >= NumberUtils.INTEGER_ZERO && i.getAvailable() <= i.getTotal();
    private Predicate<BatchesInventory> remainingPredicate = i ->
            i.getRemaining() >= NumberUtils.INTEGER_ZERO && i.getRemaining() <= i.getTotal();
    private Predicate<BatchesInventory> outboundPredicate = i ->
            i.getOutbound() >= NumberUtils.INTEGER_ZERO && i.getOutbound() <= i.getTotal();
    private Predicate<BatchesInventory> lockedPredicate = i ->
            i.getLocked() >= NumberUtils.INTEGER_ZERO && i.getLocked() <= i.getTotal();

    private void checkInventory(BatchesInventory batchesInventory) {
        // TODO: 2023/9/25
        if (!availablePredicate.test(batchesInventory)) {
            throw new BusinessException();
        }
        if (!remainingPredicate.test(batchesInventory)) {
            throw new BusinessException();
        }
        if (!outboundPredicate.test(batchesInventory)) {
            throw new BusinessException();
        }
        if (!lockedPredicate.test(batchesInventory)) {
            throw new BusinessException();
        }
    }

}
