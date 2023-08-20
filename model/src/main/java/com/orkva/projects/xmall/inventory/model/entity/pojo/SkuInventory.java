package com.orkva.projects.xmall.inventory.model.entity.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * SkuInventory
 *
 * @author Shepherd Xie
 * @version 2023/8/7
 */
@Entity
@Data
@Table(name = "vw_sku_inventories")
public class SkuInventory {
    @Id
    private Long id;
    private Integer skuId;
    private Integer total;
    private Integer remaining;
    private Integer outbound;
    private Integer available;
    private Integer locked;
}
