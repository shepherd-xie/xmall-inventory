package com.orkva.xmall.inventory.controller;

import com.orkva.xmall.inventory.entity.enums.InventoryType;
import com.orkva.xmall.inventory.entity.pojo.BatchesInventory;
import com.orkva.xmall.inventory.entity.pojo.BatchesInventoryChangeLog;
import com.orkva.xmall.inventory.entity.pojo.SkuInventory;
import com.orkva.xmall.inventory.repository.BatchesInventoryChangeLogRepository;
import com.orkva.xmall.inventory.repository.BatchesInventoryRepository;
import com.orkva.xmall.inventory.repository.SkuInventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
public class InventoryController {

    @Autowired
    private BatchesInventoryRepository batchesInventoryRepository;
    @Autowired
    private SkuInventoryRepository skuInventoryRepository;
    @Autowired
    private BatchesInventoryChangeLogRepository batchesInventoryChangeLogRepository;

    @GetMapping("/inventories/batches")
    public List<BatchesInventory> listBatchesInventories() {
        return batchesInventoryRepository.findAll();
    }

    @GetMapping("/inventories/batches/available")
    public List<BatchesInventory> listAvailableBatchesInventoriesBySkuId(@RequestParam Long skuId) {
        return batchesInventoryRepository.findAvailableBySkuId(skuId);
    }

    @GetMapping("/inventories/batches/changes")
    public List<BatchesInventoryChangeLog> listBatchesInventoryChangeLogs(@RequestBody BatchesInventoryChangeLog inventoryChangeLog) {
        return batchesInventoryChangeLogRepository.findAll(Example.of(inventoryChangeLog));
    }

    @GetMapping("/inventories/sku")
    public List<SkuInventory> listSkuInventories() {
        return skuInventoryRepository.findAll();
    }

}
