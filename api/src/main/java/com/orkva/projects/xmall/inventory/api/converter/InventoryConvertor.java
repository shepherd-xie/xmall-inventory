package com.orkva.projects.xmall.inventory.api.converter;

import com.orkva.projects.xmall.inventory.contract.record.BatchesInventoryChangeLogRecord;
import com.orkva.projects.xmall.inventory.contract.record.BatchesInventoryRecord;
import com.orkva.projects.xmall.inventory.contract.record.SkuInventoryRecord;
import com.orkva.projects.xmall.inventory.model.entity.pojo.BatchesInventory;
import com.orkva.projects.xmall.inventory.model.entity.pojo.BatchesInventoryChangeLog;
import com.orkva.projects.xmall.inventory.model.entity.pojo.SkuInventory;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * InventoryConvertor
 *
 * @author Shepherd Xie
 * @version 2023/8/21
 */
@Validated
public class InventoryConvertor {

    public static BatchesInventoryRecord toBatchesInventoryRecord(@NotNull BatchesInventory batchesInventory) {
        return new BatchesInventoryRecord(
                batchesInventory.getId(),
                batchesInventory.getLotNumber(),
                batchesInventory.getSkuId(),
                batchesInventory.getTotal(),
                batchesInventory.getRemaining(),
                batchesInventory.getOutbound(),
                batchesInventory.getAvailable(),
                batchesInventory.getLocked(),
                batchesInventory.getCreatedDate(),
                batchesInventory.getUpdatedDate()
        );
    }

    public static List<BatchesInventoryRecord> toBatchesInventoryRecords(@NotNull Collection<BatchesInventory> batchesInventories) {
        return batchesInventories.stream().map(InventoryConvertor::toBatchesInventoryRecord).collect(Collectors.toList());
    }

    public static BatchesInventoryChangeLogRecord toBatchesInventoryChangeLogRecord(@NotNull BatchesInventoryChangeLog batchesInventoryChangeLog) {
        return new BatchesInventoryChangeLogRecord(
                batchesInventoryChangeLog.getId(),
                batchesInventoryChangeLog.getLotNumber(),
                batchesInventoryChangeLog.getSkuId(),
                batchesInventoryChangeLog.getInventoryType(),
                batchesInventoryChangeLog.getTotal(),
                batchesInventoryChangeLog.getTotalChanges(),
                batchesInventoryChangeLog.getRemaining(),
                batchesInventoryChangeLog.getRemainingChanges(),
                batchesInventoryChangeLog.getOutbound(),
                batchesInventoryChangeLog.getOutboundChanges(),
                batchesInventoryChangeLog.getAvailable(),
                batchesInventoryChangeLog.getAvailableChanges(),
                batchesInventoryChangeLog.getLocked(),
                batchesInventoryChangeLog.getLockedChanges(),
                batchesInventoryChangeLog.getCreatedDate(),
                batchesInventoryChangeLog.getUpdatedDate()
        );
    }

    public static List<BatchesInventoryChangeLogRecord> toBatchesInventoryChangeLogRecords(@NotNull Collection<BatchesInventoryChangeLog> batchesInventoryChangeLogs) {
        return batchesInventoryChangeLogs.stream().map(InventoryConvertor::toBatchesInventoryChangeLogRecord).collect(Collectors.toList());
    }

    public static BatchesInventoryChangeLog toBatchesInventoryChangeLog(
            @NotNull BatchesInventoryChangeLogRecord batchesInventoryChangeLogRecord
    ) {
        BatchesInventoryChangeLog batchesInventoryChangeLog = new BatchesInventoryChangeLog();
        batchesInventoryChangeLog.setId(batchesInventoryChangeLogRecord.id());
        batchesInventoryChangeLog.setLotNumber(batchesInventoryChangeLogRecord.lotNumber());
        batchesInventoryChangeLog.setSkuId(batchesInventoryChangeLogRecord.skuId());
        batchesInventoryChangeLog.setInventoryType(batchesInventoryChangeLogRecord.inventoryType());
        batchesInventoryChangeLog.setTotal(batchesInventoryChangeLogRecord.total());
        batchesInventoryChangeLog.setTotalChanges(batchesInventoryChangeLogRecord.totalChanges());
        batchesInventoryChangeLog.setRemaining(batchesInventoryChangeLogRecord.remaining());
        batchesInventoryChangeLog.setRemainingChanges(batchesInventoryChangeLogRecord.remainingChanges());
        batchesInventoryChangeLog.setOutbound(batchesInventoryChangeLogRecord.outbound());
        batchesInventoryChangeLog.setOutboundChanges(batchesInventoryChangeLogRecord.outboundChanges());
        batchesInventoryChangeLog.setAvailable(batchesInventoryChangeLogRecord.available());
        batchesInventoryChangeLog.setAvailableChanges(batchesInventoryChangeLogRecord.availableChanges());
        batchesInventoryChangeLog.setLocked(batchesInventoryChangeLogRecord.locked());
        batchesInventoryChangeLog.setLockedChanges(batchesInventoryChangeLogRecord.lockedChanges());
        batchesInventoryChangeLog.setCreatedDate(batchesInventoryChangeLogRecord.createdDate());
        batchesInventoryChangeLog.setUpdatedDate(batchesInventoryChangeLogRecord.updatedDate());
        return batchesInventoryChangeLog;
    }

    public static SkuInventoryRecord toSkuInventoryRecord(@NotNull SkuInventory skuInventory) {
        Long id = skuInventory.getId();
        Integer skuId = skuInventory.getSkuId();
        Integer total = skuInventory.getTotal();
        Integer remaining = skuInventory.getRemaining();
        Integer outbound = skuInventory.getOutbound();
        Integer available = skuInventory.getAvailable();
        Integer locked = skuInventory.getLocked();

        return new SkuInventoryRecord(
                id, skuId, total, remaining, outbound, available, locked
        );
    }

    public static List<SkuInventoryRecord> toSkuInventoryRecords(@NotNull Collection<SkuInventory> skuInventories) {
        return skuInventories.stream().map(InventoryConvertor::toSkuInventoryRecord).collect(Collectors.toList());
    }

}
