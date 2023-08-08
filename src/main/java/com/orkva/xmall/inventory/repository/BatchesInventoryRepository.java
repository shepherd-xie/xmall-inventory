package com.orkva.xmall.inventory.repository;

import com.orkva.xmall.inventory.entity.pojo.BatchesInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * BatchesInventoryRepository
 *
 * @author Shepherd Xie
 * @version 2023/8/7
 */
public interface BatchesInventoryRepository extends JpaRepository<BatchesInventory, Long> {

    @Query("select bi from BatchesInventory bi where bi.skuId = :skuId and bi.available <> 0")
    List<BatchesInventory> findAvailableBySkuId(Long skuId);

    Optional<BatchesInventory> findByLotNumber(String lotNumber);

}
