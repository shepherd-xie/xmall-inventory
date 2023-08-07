package com.orkva.xmall.inventory.entity.pojo;

import jakarta.persistence.*;
import lombok.Data;

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
    @Column(unique = true)
    private String lotNumber;
    private Long skuId;
    private Integer total;
    private Integer remaining;
    private Integer outbound;
    private Integer available;
    private Integer locked;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant createdDate;
    private Instant updatedDate;
}
