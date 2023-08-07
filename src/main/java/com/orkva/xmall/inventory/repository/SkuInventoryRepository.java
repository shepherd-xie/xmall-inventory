package com.orkva.xmall.inventory.repository;

import com.orkva.xmall.inventory.entity.pojo.SkuInventory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * SkuInventoryRepository
 *
 * @author Shepherd Xie
 * @version 2023/8/7
 */
public interface SkuInventoryRepository extends JpaRepository<SkuInventory, Long> {
}
