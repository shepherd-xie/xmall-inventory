package com.orkva.projects.xmall.inventory.model.entity.pojo;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import java.time.Instant;

/**
 * BatchesInventory
 *
 * @author Shepherd Xie
 * @version 2023/8/4
 */
@Entity
@Data
@Table(name = "tb_batches_inventories")
public class BatchesInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 批次号
     */
    @Column(unique = true)
    private String lotNumber;
    private Long skuId;
    /**
     * 总量
     */
    private Integer total;
    /**
     * 剩余量
     */
    private Integer remaining;
    /**
     * 出库量
     */
    private Integer outbound;
    /**
     * 可用量
     */
    private Integer available;
    /**
     * 锁定量
     */
    private Integer locked;

    @Generated
    private Instant createdDate;
    @Generated(event = {EventType.INSERT, EventType.UPDATE})
    private Instant updatedDate;
}
