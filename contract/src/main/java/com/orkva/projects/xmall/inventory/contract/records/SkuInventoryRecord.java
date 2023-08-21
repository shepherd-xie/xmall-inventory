package com.orkva.projects.xmall.inventory.contract.records;

/**
 * SkuInventoryRecord
 *
 * @author Shepherd Xie
 * @version 2023/8/21
 */
public record SkuInventoryRecord(
        Long id,
        Integer skuId,
        Integer total,
        Integer remaining,
        Integer outbound,
        Integer available,
        Integer locked
) {
}
