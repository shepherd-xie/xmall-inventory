package com.orkva.projects.xmall.inventory.api.controller;

import com.orkva.projects.xmall.inventory.api.converter.InventoryConvertor;
import com.orkva.projects.xmall.inventory.business.service.BatchesInventoriesService;
import com.orkva.projects.xmall.inventory.contract.controller.InventoryContractController;
import com.orkva.projects.xmall.inventory.contract.records.*;
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

    @Override
    public JsonResult<List<BatchesInventoryRecord>> listBatchesInventories() {
        return JsonResult.ok(InventoryConvertor.toBatchesInventoryRecords(batchesInventoryRepository.findAll()));
    }

    @Override
    public JsonResult<List<BatchesInventoryRecord>> listAvailableBatchesInventoriesBySkuId(@NotNull Long skuId) {
        return JsonResult.ok(InventoryConvertor.toBatchesInventoryRecords(batchesInventoryRepository.findAvailableBySkuId(skuId)));
    }

    @Override
    public JsonResult<List<BatchesInventoryChangeLogRecord>> listBatchesInventoryChangeLogs(
            @RequestBody BatchesInventoryChangeLogRecord batchesInventoryChangeLogRecord
    ) {
        List<BatchesInventoryChangeLog> batchesInventoryChangeLogs = batchesInventoryChangeLogRepository
                .findAll(Example.of(InventoryConvertor.toBatchesInventoryChangeLog(batchesInventoryChangeLogRecord)));
        return JsonResult.ok(InventoryConvertor.toBatchesInventoryChangeLogRecords(batchesInventoryChangeLogs));
    }

    @Override
    public JsonResult<List<SkuInventoryRecord>> listSkuInventories() {
        return JsonResult.ok(InventoryConvertor.toSkuInventoryRecords(skuInventoryRepository.findAll()));
    }

    @Override
    public JsonResult<BatchesInventoryRecord> purchase(@RequestBody InventoryPurchaseParamsRecord paramsRecord) {
        BatchesInventory batchesInventory = batchesInventoriesService.purchase(paramsRecord.skuId(), paramsRecord.change());
        return JsonResult.ok(InventoryConvertor.toBatchesInventoryRecord(batchesInventory));
    }

    @Override
    public JsonResult<BatchesInventoryRecord> locked(@RequestBody InventoryChangeParamsRecord paramsRecord) {
        BatchesInventory batchesInventory = batchesInventoriesService.locked(paramsRecord.lotNumber(), paramsRecord.change());
        return JsonResult.ok(InventoryConvertor.toBatchesInventoryRecord(batchesInventory));
    }

    @Override
    public JsonResult<BatchesInventoryRecord> pickup(@RequestBody InventoryChangeParamsRecord paramsRecord) {
        BatchesInventory batchesInventory = batchesInventoriesService.pickup(paramsRecord.lotNumber(), paramsRecord.change());
        return JsonResult.ok(InventoryConvertor.toBatchesInventoryRecord(batchesInventory));
    }

    @Override
    public JsonResult<BatchesInventoryRecord> recede(@RequestBody InventoryChangeParamsRecord paramsRecord) {
        BatchesInventory batchesInventory = batchesInventoriesService.recede(paramsRecord.lotNumber(), paramsRecord.change());
        return JsonResult.ok(InventoryConvertor.toBatchesInventoryRecord(batchesInventory));
    }

}
