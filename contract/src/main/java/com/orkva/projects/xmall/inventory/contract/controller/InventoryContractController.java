package com.orkva.projects.xmall.inventory.contract.controller;

import com.orkva.projects.xmall.inventory.contract.Routers;
import com.orkva.projects.xmall.inventory.contract.records.*;
import com.orkva.xmall.common.entity.JsonResult;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * InventoryContractController
 *
 * @author Shepherd Xie
 * @version 2023/8/21
 */
@Validated
public interface InventoryContractController {

    @GetMapping(Routers.Inventory.LIST_BATCHES_INVENTORIES)
    JsonResult<List<BatchesInventoryRecord>> listBatchesInventories();

    @GetMapping(Routers.Inventory.LIST_AVAILABLE_BATCHES_INVENTORIES)
    JsonResult<List<BatchesInventoryRecord>> listAvailableBatchesInventoriesBySkuId(@NotNull Long skuId);

    @GetMapping(Routers.Inventory.LIST_BATCHES_INVENTORY_CHANGE_LOGS)
    JsonResult<List<BatchesInventoryChangeLogRecord>> listBatchesInventoryChangeLogs(@RequestBody BatchesInventoryChangeLogRecord inventoryChangeLog);

    @GetMapping(Routers.Inventory.LIST_SKU_INVENTORIES)
    JsonResult<List<SkuInventoryRecord>> listSkuInventories();

    @PostMapping(Routers.Inventory.PURCHASE)
    JsonResult<BatchesInventoryRecord> purchase(@RequestBody InventoryPurchaseParamsRecord paramsRecord);

    @PostMapping(Routers.Inventory.LOCKED)
    JsonResult<BatchesInventoryRecord> locked(@RequestBody InventoryChangeParamsRecord paramsRecord);

    @PostMapping(Routers.Inventory.PICKUP)
    JsonResult<BatchesInventoryRecord> pickup(@RequestBody InventoryChangeParamsRecord paramsRecord);

    @PostMapping(Routers.Inventory.RECEDE)
    JsonResult<BatchesInventoryRecord> recede(@RequestBody InventoryChangeParamsRecord paramsRecord);

}
