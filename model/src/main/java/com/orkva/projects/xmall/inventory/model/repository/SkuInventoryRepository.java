package com.orkva.projects.xmall.inventory.model.repository;

import com.orkva.projects.xmall.inventory.model.entity.pojo.SkuInventory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * SkuInventoryRepository
 *
 * @author Shepherd Xie
 * @version 2023/8/7
 */
public interface SkuInventoryRepository extends JpaRepository<SkuInventory, Long> {
}
