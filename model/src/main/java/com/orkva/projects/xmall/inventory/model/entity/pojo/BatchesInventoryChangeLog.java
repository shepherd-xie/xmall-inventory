package com.orkva.projects.xmall.inventory.model.entity.pojo;

import com.orkva.projects.xmall.inventory.common.entity.InventoryType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lotNumber;
    private Long skuId;
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

    @Generated
    private Instant createdDate;
    @Generated(event = {EventType.INSERT, EventType.UPDATE})
    private Instant updatedDate;
}
