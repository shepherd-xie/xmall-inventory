package com.orkva.projects.xmall.inventory.contract.records;

import jakarta.validation.constraints.NotNull;

/**
 * InventoryPurchaseParamsRecord
 *
 * @author Shepherd Xie
 * @version 2023/8/9
 */
public record InventoryPurchaseParamsRecord(
        @NotNull
        Long skuId,
        @NotNull
        Integer change
) {

}
