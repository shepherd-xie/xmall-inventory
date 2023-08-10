package com.orkva.xmall.inventory.controller;

import com.orkva.xmall.inventory.common.JsonResult;
import com.orkva.xmall.inventory.controller.records.InventoryChangeParamsRecord;
import com.orkva.xmall.inventory.controller.records.InventoryPurchaseParamsRecord;
import com.orkva.xmall.inventory.entity.pojo.BatchesInventory;
import com.orkva.xmall.inventory.entity.pojo.BatchesInventoryChangeLog;
import com.orkva.xmall.inventory.entity.pojo.SkuInventory;
import com.orkva.xmall.inventory.repository.BatchesInventoryChangeLogRepository;
import com.orkva.xmall.inventory.repository.BatchesInventoryRepository;
import com.orkva.xmall.inventory.repository.SkuInventoryRepository;
import com.orkva.xmall.inventory.service.BatchesInventoriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * InventoryController
 *
 * @author Shepherd Xie
 * @version 2023/8/7
 */
@Slf4j
@RestController
public class InventoryController {

    @Autowired
    private BatchesInventoryRepository batchesInventoryRepository;
    @Autowired
    private SkuInventoryRepository skuInventoryRepository;
    @Autowired
    private BatchesInventoryChangeLogRepository batchesInventoryChangeLogRepository;
    @Autowired
    private BatchesInventoriesService batchesInventoriesService;

    @GetMapping("/inventories/batches")
    public JsonResult<List<BatchesInventory>> listBatchesInventories() {
        return JsonResult.ok(batchesInventoryRepository.findAll());
    }

    @GetMapping("/inventories/batches/available")
    public JsonResult<List<BatchesInventory>> listAvailableBatchesInventoriesBySkuId(@RequestParam Long skuId) {
        return JsonResult.ok(batchesInventoryRepository.findAvailableBySkuId(skuId));
    }

    @GetMapping("/inventories/batches/changes")
    public JsonResult<List<BatchesInventoryChangeLog>> listBatchesInventoryChangeLogs(@RequestBody BatchesInventoryChangeLog inventoryChangeLog) {
        return JsonResult.ok(batchesInventoryChangeLogRepository.findAll(Example.of(inventoryChangeLog)));
    }

    @GetMapping("/inventories/sku")
    public JsonResult<List<SkuInventory>> listSkuInventories() {
        return JsonResult.ok(skuInventoryRepository.findAll());
    }

    @PostMapping(value = "/inventories/purchase")
    public JsonResult<BatchesInventory> purchase(@RequestBody InventoryPurchaseParamsRecord paramsRecord) {
        log.info("{}", paramsRecord);
        return JsonResult.ok(batchesInventoriesService.purchase(paramsRecord.skuId(), paramsRecord.change()));
    }

    @PostMapping(value = "/inventories/locked")
    public JsonResult<BatchesInventory> locked(@RequestBody InventoryChangeParamsRecord paramsRecord) {
        log.info("{}", paramsRecord);
        return JsonResult.ok(batchesInventoriesService.locked(paramsRecord.lotNumber(), paramsRecord.change()));
    }

    @PostMapping(value = "/inventories/pickup")
    public JsonResult<BatchesInventory> pickup(@RequestBody InventoryChangeParamsRecord paramsRecord) {
        log.info("{}", paramsRecord);
        return JsonResult.ok(batchesInventoriesService.pickup(paramsRecord.lotNumber(), paramsRecord.change()));
    }

    @PostMapping(value = "/inventories/recede")
    public JsonResult<BatchesInventory> recede(@RequestBody InventoryChangeParamsRecord paramsRecord) {
        log.info("{}", paramsRecord);
        return JsonResult.ok(batchesInventoriesService.recede(paramsRecord.lotNumber(), paramsRecord.change()));
    }

}
