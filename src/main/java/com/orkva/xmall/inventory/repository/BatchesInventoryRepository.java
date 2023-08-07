package com.orkva.xmall.inventory.repository;

import com.orkva.xmall.inventory.entity.pojo.BatchesInventory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * BatchesInventoryRepository
 *
 * @author Shepherd Xie
 * @version 2023/8/7
 */
public interface BatchesInventoryRepository extends JpaRepository<BatchesInventory, Long> {
}
