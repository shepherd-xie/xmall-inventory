package com.orkva.xmall.inventory.repository;

import com.orkva.xmall.inventory.entity.pojo.BatchesInventoryChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * BatchesInventoryChangeLogRepository
 *
 * @author Shepherd Xie
 * @version 2023/8/7
 */
public interface BatchesInventoryChangeLogRepository extends JpaRepository<BatchesInventoryChangeLog, Long> {
}
