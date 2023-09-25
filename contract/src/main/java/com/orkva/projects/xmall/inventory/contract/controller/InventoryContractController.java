package com.orkva.projects.xmall.inventory.contract.controller;

import com.orkva.projects.xmall.inventory.contract.Routers;
import com.orkva.projects.xmall.inventory.contract.record.*;
import com.orkva.xmall.common.entity.JsonResult;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 库存数据契约类
 *
 * @author Shepherd Xie
 * @version 2023/8/21
 */
@Validated
public interface InventoryContractController {

    /**
     * 获取批次库存记录列表
     *
     * @param batchesInventoriesQuery 查询条件
     * @return 包含批次库存记录的 JSON 结果
     */
    @GetMapping(Routers.Inventory.LIST_BATCHES_INVENTORIES)
    JsonResult<List<BatchesInventoryRecord>> listBatchesInventories(BatchesInventoriesQuery batchesInventoriesQuery);

    /**
     * 根据 SKU ID 获取可用批次库存记录列表
     *
     * @param skuId SKU ID
     * @return 包含可用批次库存记录的 JSON 结果
     */
    @GetMapping(Routers.Inventory.LIST_AVAILABLE_BATCHES_INVENTORIES)
    JsonResult<List<BatchesInventoryRecord>> listAvailableBatchesInventoriesBySkuId(@NotNull Long skuId);

    /**
     * 获取批次库存变更记录列表
     *
     * @param batchesInventoryChangeLogRecord 批次库存变更记录参数
     * @return 包含批次库存变更记录的 JSON 结果
     */
    @GetMapping(Routers.Inventory.LIST_BATCHES_INVENTORY_CHANGE_LOGS)
    JsonResult<List<BatchesInventoryChangeLogRecord>> listBatchesInventoryChangeLogs(
            @RequestBody BatchesInventoryChangeLogRecord batchesInventoryChangeLogRecord
    );

    /**
     * 获取 SKU 库存记录列表
     *
     * @return 包含 SKU 库存记录的 JSON 结果
     */
    @GetMapping(Routers.Inventory.LIST_SKU_INVENTORIES)
    JsonResult<List<SkuInventoryRecord>> listSkuInventories();

    /**
     * 执行库存购买操作
     *
     * @param paramsRecord 库存购买参数记录
     * @return 包含购买后的批次库存记录的 JSON 结果
     */
    @PostMapping(Routers.Inventory.PURCHASE)
    JsonResult<BatchesInventoryRecord> purchase(@RequestBody InventoryPurchaseParamsRecord paramsRecord);

    /**
     * 执行库存锁定操作
     *
     * @param paramsRecord 库存变更参数记录
     * @return 包含锁定后的批次库存记录的 JSON 结果
     */
    @PostMapping(Routers.Inventory.LOCKED)
    JsonResult<BatchesInventoryRecord> locked(@RequestBody InventoryChangeParamsRecord paramsRecord);

    /**
     * 执行库存提取操作
     *
     * @param paramsRecord 库存变更参数记录
     * @return 包含提取后的批次库存记录的 JSON 结果
     */
    @PostMapping(Routers.Inventory.PICKUP)
    JsonResult<BatchesInventoryRecord> pickup(@RequestBody InventoryChangeParamsRecord paramsRecord);

    /**
     * 执行库存归还操作
     *
     * @param paramsRecord 库存变更参数记录
     * @return 包含归还后的批次库存记录的 JSON 结果
     */
    @PostMapping(Routers.Inventory.RECEDE)
    JsonResult<BatchesInventoryRecord> recede(@RequestBody InventoryChangeParamsRecord paramsRecord);

}
