package com.orkva.projects.xmall.inventory.contract.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * InventoryChangeParamsRecord
 *
 * @author Shepherd Xie
 * @version 2023/8/10
 */
public record InventoryChangeParamsRecord(
        @NotBlank
        String lotNumber,
        @NotNull
        Integer change
) {
}
