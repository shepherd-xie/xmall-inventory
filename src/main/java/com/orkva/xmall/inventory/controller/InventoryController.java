package com.orkva.xmall.inventory.controller;

import com.orkva.xmall.inventory.entity.pojo.BatchesInventory;
import com.orkva.xmall.inventory.entity.pojo.SkuInventory;
import com.orkva.xmall.inventory.repository.BatchesInventoryRepository;
import com.orkva.xmall.inventory.repository.SkuInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * InventoryController
 *
 * @author Shepherd Xie
 * @version 2023/8/7
 */
@RestController
public class InventoryController {

    @Autowired
    private BatchesInventoryRepository batchesInventoryRepository;
    @Autowired
    private SkuInventoryRepository skuInventoryRepository;

    @GetMapping("/inventory/batches")
    public List<BatchesInventory> listBatchesInventories() {
        return batchesInventoryRepository.findAll();
    }

    @GetMapping("/inventory/sku")
    public List<SkuInventory> listSkuInventories() {
        return skuInventoryRepository.findAll();
    }

}
