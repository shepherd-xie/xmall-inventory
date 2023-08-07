package com.orkva.xmall.inventory.service;

import com.orkva.xmall.inventory.repository.BatchesInventoryChangeLogRepository;
import com.orkva.xmall.inventory.repository.BatchesInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * InventoryService
 *
 * @author Shepherd Xie
 * @version 2023/8/7
 */
@Service
@Transactional
public class InventoryService {

    @Autowired
    private BatchesInventoryRepository batchesInventoryRepository;
    @Autowired
    private BatchesInventoryChangeLogRepository batchesInventoryChangeLogRepository;

    public void locked() {

    }

    public void outbound() {

    }

    public void inbound() {

    }

}
