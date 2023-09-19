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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
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

    @PreAuthorize("authentication")
    @GetMapping("/user")
    public JsonResult<Principal> user(Principal principal) {
        return JsonResult.ok(principal);
    }

    @GetMapping(Routers.Inventory.LIST_BATCHES_INVENTORIES)
    @Override
    public JsonResult<List<BatchesInventoryRecord>> listBatchesInventories() {
        return JsonResult.ok(InventoryConvertor.toBatchesInventoryRecords(batchesInventoryRepository.findAll()));
    }

    @GetMapping(Routers.Inventory.PAGE_BATCHES_INVENTORIES)
    public JsonResult<Page<BatchesInventoryRecord>> pageBatchesInventories(
            BatchesInventoriesQuery batchesInventoriesQuery,
            @PageableDefault Pageable pageable
    ) {
        Page<BatchesInventory> batchesInventoryPage = batchesInventoryRepository
                .findAll(Example.of(InventoryConvertor.toBatchesInventory(batchesInventoriesQuery)), pageable);
        return JsonResult.ok(InventoryConvertor.toBatchesInventoryRecords(batchesInventoryPage, pageable));
    }

    @GetMapping(Routers.Inventory.LIST_AVAILABLE_BATCHES_INVENTORIES)
    @Override
    public JsonResult<List<BatchesInventoryRecord>> listAvailableBatchesInventoriesBySkuId(@NotNull Long skuId) {
        return JsonResult.ok(InventoryConvertor.toBatchesInventoryRecords(batchesInventoryRepository.findAvailableBySkuId(skuId)));
    }

    @GetMapping(Routers.Inventory.LIST_BATCHES_INVENTORY_CHANGE_LOGS)
    @Override
    public JsonResult<List<BatchesInventoryChangeLogRecord>> listBatchesInventoryChangeLogs(
            @RequestBody BatchesInventoryChangeLogRecord batchesInventoryChangeLogRecord
    ) {
        List<BatchesInventoryChangeLog> batchesInventoryChangeLogs = batchesInventoryChangeLogRepository
                .findAll(Example.of(InventoryConvertor.toBatchesInventoryChangeLog(batchesInventoryChangeLogRecord)));
        return JsonResult.ok(InventoryConvertor.toBatchesInventoryChangeLogRecords(batchesInventoryChangeLogs));
    }

    @GetMapping(Routers.Inventory.LIST_SKU_INVENTORIES)
    @Override
    public JsonResult<List<SkuInventoryRecord>> listSkuInventories() {
        return JsonResult.ok(InventoryConvertor.toSkuInventoryRecords(skuInventoryRepository.findAll()));
    }

    @PostMapping(Routers.Inventory.PURCHASE)
    @Override
    public JsonResult<BatchesInventoryRecord> purchase(@RequestBody InventoryPurchaseParamsRecord paramsRecord) {
        BatchesInventory batchesInventory = batchesInventoriesService.purchase(paramsRecord.skuId(), paramsRecord.change());
        return JsonResult.ok(InventoryConvertor.toBatchesInventoryRecord(batchesInventory));
    }

    @PostMapping(Routers.Inventory.LOCKED)
    @Override
    public JsonResult<BatchesInventoryRecord> locked(@RequestBody InventoryChangeParamsRecord paramsRecord) {
        BatchesInventory batchesInventory = batchesInventoriesService.locked(paramsRecord.lotNumber(), paramsRecord.change());
        return JsonResult.ok(InventoryConvertor.toBatchesInventoryRecord(batchesInventory));
    }

    @PostMapping(Routers.Inventory.PICKUP)
    @Override
    public JsonResult<BatchesInventoryRecord> pickup(@RequestBody InventoryChangeParamsRecord paramsRecord) {
        BatchesInventory batchesInventory = batchesInventoriesService.pickup(paramsRecord.lotNumber(), paramsRecord.change());
        return JsonResult.ok(InventoryConvertor.toBatchesInventoryRecord(batchesInventory));
    }

    @PostMapping(Routers.Inventory.RECEDE)
    @Override
    public JsonResult<BatchesInventoryRecord> recede(@RequestBody InventoryChangeParamsRecord paramsRecord) {
        BatchesInventory batchesInventory = batchesInventoriesService.recede(paramsRecord.lotNumber(), paramsRecord.change());
        return JsonResult.ok(InventoryConvertor.toBatchesInventoryRecord(batchesInventory));
    }

}
