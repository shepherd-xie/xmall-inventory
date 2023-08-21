package com.orkva.projects.xmall.inventory.api.controller;

import com.orkva.projects.xmall.inventory.business.service.BatchesInventoriesService;
import com.orkva.projects.xmall.inventory.contract.controller.InventoryContractController;
import com.orkva.projects.xmall.inventory.contract.records.*;
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
        return JsonResult.ok(batchesInventoryRepository.findAll());
    }

    @Override
    public JsonResult<List<BatchesInventoryRecord>> listAvailableBatchesInventoriesBySkuId(@NotNull Long skuId) {
        return JsonResult.ok(batchesInventoryRepository.findAvailableBySkuId(skuId));
    }

    @Override
    public JsonResult<List<BatchesInventoryChangeLogRecord>> listBatchesInventoryChangeLogs(@RequestBody BatchesInventoryChangeLogRecord inventoryChangeLog) {
        return JsonResult.ok(batchesInventoryChangeLogRepository.findAll(Example.of(inventoryChangeLog)));
    }

    @Override
    public JsonResult<List<SkuInventoryRecord>> listSkuInventories() {
        return JsonResult.ok(skuInventoryRepository.findAll());
    }

    @Override
    public JsonResult<BatchesInventoryRecord> purchase(@RequestBody InventoryPurchaseParamsRecord paramsRecord) {
        return JsonResult.ok(batchesInventoriesService.purchase(paramsRecord.skuId(), paramsRecord.change()));
    }

    @Override
    public JsonResult<BatchesInventoryRecord> locked(@RequestBody InventoryChangeParamsRecord paramsRecord) {
        return JsonResult.ok(batchesInventoriesService.locked(paramsRecord.lotNumber(), paramsRecord.change()));
    }

    @Override
    public JsonResult<BatchesInventoryRecord> pickup(@RequestBody InventoryChangeParamsRecord paramsRecord) {
        return JsonResult.ok(batchesInventoriesService.pickup(paramsRecord.lotNumber(), paramsRecord.change()));
    }

    @Override
    public JsonResult<BatchesInventoryRecord> recede(@RequestBody InventoryChangeParamsRecord paramsRecord) {
        return JsonResult.ok(batchesInventoriesService.recede(paramsRecord.lotNumber(), paramsRecord.change()));
    }

}
