package com.orkva.projects.xmall.inventory.contract.record;

import com.orkva.projects.xmall.inventory.common.entity.InventoryType;

import java.time.Instant;

/**
 * BatchesInventoryChangeLogRecord
 *
 * @author Shepherd Xie
 * @version 2023/8/21
 */
public record BatchesInventoryChangeLogRecord(
        Long id,
        String lotNumber,
        Long skuId,
        InventoryType inventoryType,
        Integer total,
        Integer totalChanges,
        Integer remaining,
        Integer remainingChanges,
        Integer outbound,
        Integer outboundChanges,
        Integer available,
        Integer availableChanges,
        Integer locked,
        Integer lockedChanges,
        Instant createdDate,
        Instant updatedDate
) {
}
