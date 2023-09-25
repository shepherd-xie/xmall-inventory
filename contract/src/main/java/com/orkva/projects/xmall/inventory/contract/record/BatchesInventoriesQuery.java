package com.orkva.projects.xmall.inventory.contract.record;

/**
 * BatchesInventoriesQuery
 *
 * @author Shepherd Xie
 * @version 2023/8/22
 */
public record BatchesInventoriesQuery(
        Long id,
        String lotNumber,
        Long skuId
) {
}
