package com.orkva.projects.xmall.inventory.model.repository;

import com.orkva.projects.xmall.inventory.model.entity.pojo.BatchesInventoryChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * BatchesInventoryChangeLogRepository
 *
 * @author Shepherd Xie
 * @version 2023/8/7
 */
public interface BatchesInventoryChangeLogRepository extends JpaRepository<BatchesInventoryChangeLog, Long> {
}
