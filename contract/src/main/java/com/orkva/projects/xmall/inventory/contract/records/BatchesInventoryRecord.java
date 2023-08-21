package com.orkva.projects.xmall.inventory.contract.records;

import java.time.Instant;

/**
 * BatchesInventoryResultRecord
 *
 * @author Shepherd Xie
 * @version 2023/8/21
 */
public record BatchesInventoryRecord(
        Long id,
        String lotNumber,
        Long skuId,
        Integer total,
        Integer remaining,
        Integer outbound,
        Integer available,
        Integer locked,
        Instant createdDate,
        Instant updatedDate
) {
}
