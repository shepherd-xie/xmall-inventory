package com.orkva.projects.xmall.inventory.api.controller;

import com.orkva.projects.xmall.inventory.api.converter.InventoryConvertor;
import com.orkva.projects.xmall.inventory.api.query.BatchesInventoriesQuery;
import com.orkva.projects.xmall.inventory.business.service.BatchesInventoriesService;
import com.orkva.projects.xmall.inventory.contract.Routers;
import com.orkva.projects.xmall.inventory.contract.controller.InventoryContractController;
import com.orkva.projects.xmall.inventory.contract.record.*;
import com.orkva.projects.xmall.inventory.model.entity.pojo.BatchesInventory;
import com.orkva.projects.xmall.inventory.model.entity.pojo.BatchesInventoryChangeLog;
import com.orkva.projects.xmall.inventory.model.repository.BatchesInventoryChangeLogRepository;
import com.orkva.projects.xmall.inventory.model.repository.BatchesInventoryRepository;
import com.orkva.projects.xmall.inventory.model.repository.SkuInventoryRepository;
import com.orkva.xmall.common.entity.JsonResult;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * InventoryController
 *
 * @author Shepherd Xie
 * @version 2023/8/7
 */
@Slf4j
@RestController
public class InventoryController implements InventoryContractController {

    @Autowired
    private BatchesInventoryRepository batchesInventoryRepository;
    @Autowired
    private SkuInventoryRepository skuInventoryRepository;
    @Autowired
    private BatchesInventoryChangeLogRepository batchesInventoryChangeLogRepository;
    @Autowired
    private BatchesInventoriesService batchesInventoriesService;

    /**
     * 获取批次库存列表
     *
     * @return JsonResult 包含批次库存记录的列表
     */
    @GetMapping(Routers.Inventory.LIST_BATCHES_INVENTORIES)
    @Override
    public JsonResult<List<BatchesInventoryRecord>> listBatchesInventories() {
        return JsonResult.ok(InventoryConvertor.toBatchesInventoryRecords(batchesInventoryRepository.findAll()));
    }

    /**
     * 分页获取批次库存列表
     *
     * @param batchesInventoriesQuery 查询条件
     * @param pageable                分页参数
     * @return JsonResult 包含分页批次库存记录的结果
     */
    @GetMapping(Routers.Inventory.PAGE_BATCHES_INVENTORIES)
    public JsonResult<Page<BatchesInventoryRecord>> pageBatchesInventories(
            BatchesInventoriesQuery batchesInventoriesQuery,
            @PageableDefault Pageable pageable
    ) {
        Page<BatchesInventory> batchesInventoryPage = batchesInventoryRepository
                .findAll(Example.of(InventoryConvertor.toBatchesInventory(batchesInventoriesQuery)), pageable);
        return JsonResult.ok(InventoryConvertor.toBatchesInventoryRecords(batchesInventoryPage, pageable));
    }

    /**
     * 获取可用的批次库存列表（根据SKU ID）
     *
     * @param skuId SKU ID
     * @return JsonResult 包含可用批次库存记录的列表
     */
    @GetMapping(Routers.Inventory.LIST_AVAILABLE_BATCHES_INVENTORIES)
    @Override
    public JsonResult<List<BatchesInventoryRecord>> listAvailableBatchesInventoriesBySkuId(@NotNull Long skuId) {
        return JsonResult.ok(InventoryConvertor.toBatchesInventoryRecords(batchesInventoryRepository.findAvailableBySkuId(skuId)));
    }

    /**
     * 获取批次库存变更日志列表
     *
     * @param batchesInventoryChangeLogRecord 查询条件
     * @return JsonResult 包含批次库存变更日志记录的列表
     */
    @GetMapping(Routers.Inventory.LIST_BATCHES_INVENTORY_CHANGE_LOGS)
    @Override
    public JsonResult<List<BatchesInventoryChangeLogRecord>> listBatchesInventoryChangeLogs(
            @RequestBody BatchesInventoryChangeLogRecord batchesInventoryChangeLogRecord
    ) {
        List<BatchesInventoryChangeLog> batchesInventoryChangeLogs = batchesInventoryChangeLogRepository
                .findAll(Example.of(InventoryConvertor.toBatchesInventoryChangeLog(batchesInventoryChangeLogRecord)));
        return JsonResult.ok(InventoryConvertor.toBatchesInventoryChangeLogRecords(batchesInventoryChangeLogs));
    }

    /**
     * 获取SKU库存列表
     *
     * @return JsonResult 包含SKU库存记录的列表
     */
    @GetMapping(Routers.Inventory.LIST_SKU_INVENTORIES)
    @Override
    public JsonResult<List<SkuInventoryRecord>> listSkuInventories() {
        return JsonResult.ok(InventoryConvertor.toSkuInventoryRecords(skuInventoryRepository.findAll()));
    }

    /**
     * 购买库存
     *
     * @param paramsRecord 购买参数记录
     * @return JsonResult 包含购买后的批次库存记录
     */
    @PostMapping(Routers.Inventory.PURCHASE)
    @Override
    public JsonResult<BatchesInventoryRecord> purchase(@RequestBody InventoryPurchaseParamsRecord paramsRecord) {
        BatchesInventory batchesInventory = batchesInventoriesService.purchase(paramsRecord.skuId(), paramsRecord.change());
        return JsonResult.ok(InventoryConvertor.toBatchesInventoryRecord(batchesInventory));
    }

    /**
     * 锁定库存
     *
     * @param paramsRecord 锁定参数记录
     * @return JsonResult 包含锁定后的批次库存记录
     */
    @PostMapping(Routers.Inventory.LOCKED)
    @Override
    public JsonResult<BatchesInventoryRecord> locked(@RequestBody InventoryChangeParamsRecord paramsRecord) {
        BatchesInventory batchesInventory = batchesInventoriesService.locked(paramsRecord.lotNumber(), paramsRecord.change());
        return JsonResult.ok(InventoryConvertor.toBatchesInventoryRecord(batchesInventory));
    }

    /**
     * 取货库存
     *
     * @param paramsRecord 取货参数记录
     * @return JsonResult 包含取货后的批次库存记录
     */
    @PostMapping(Routers.Inventory.PICKUP)
    @Override
    public JsonResult<BatchesInventoryRecord> pickup(@RequestBody InventoryChangeParamsRecord paramsRecord) {
        BatchesInventory batchesInventory = batchesInventoriesService.pickup(paramsRecord.lotNumber(), paramsRecord.change());
        return JsonResult.ok(InventoryConvertor.toBatchesInventoryRecord(batchesInventory));
    }

    /**
     * 退货库存
     *
     * @param paramsRecord 退货参数记录
     * @return JsonResult 包含退货后的批次库存记录
     */
    @PostMapping(Routers.Inventory.RECEDE)
    @Override
    public JsonResult<BatchesInventoryRecord> recede(@RequestBody InventoryChangeParamsRecord paramsRecord) {
        BatchesInventory batchesInventory = batchesInventoriesService.recede(paramsRecord.lotNumber(), paramsRecord.change());
        return JsonResult.ok(InventoryConvertor.toBatchesInventoryRecord(batchesInventory));
    }

}
