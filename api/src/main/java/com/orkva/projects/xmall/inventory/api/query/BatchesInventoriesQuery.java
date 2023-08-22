package com.orkva.projects.xmall.inventory.api.query;

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
