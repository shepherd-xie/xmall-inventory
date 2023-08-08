package com.orkva.xmall.inventory.entity.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.orkva.xmall.inventory.entity.enums.InventoryType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

/**
 * BatchesInventoryChangeLog
 *
 * @author Shepherd Xie
 * @version 2023/8/7
 */
@Entity
@Data
@Table(name = "tb_batches_inventories_change_logs")
public class BatchesInventoryChangeLog {
    private String lotNumber;
    private Integer skuId;
    @Enumerated(EnumType.STRING)
    private InventoryType inventoryType;
    private Integer total;
    private Integer totalChanges;
    private Integer remaining;
    private Integer remainingChanges;
    private Integer outbound;
    private Integer outboundChanges;
    private Integer available;
    private Integer availableChanges;
    private Integer locked;
    private Integer lockedChanges;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant createdDate;
    private Instant updatedDate;
}
