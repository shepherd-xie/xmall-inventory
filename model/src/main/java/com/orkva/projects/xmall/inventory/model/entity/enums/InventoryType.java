package com.orkva.projects.xmall.inventory.model.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * InventoryType
 *
 * @author Shepherd Xie
 * @version 2023/8/8
 */
@Getter
@ToString
@AllArgsConstructor
public enum InventoryType {
    INIT(0, "初始化", "init", null),

    // INBOUND

    INBOUND(100, "入库", "inbound", null),
    PURCHASE(101, "采购", "purchase", INBOUND),
    RECEDE(102, "退货入库", "recede", INBOUND),

    // OUTBOUND

    OUTBOUND(200, "出库", "outbound", null),
    LOCKED(201, "锁定", "locked", OUTBOUND),
    PICKUP(202, "提货", "pickup", OUTBOUND),

    // OTHER

    OTHER_INBOUND(199, "其他入库", "other_inbound", INBOUND),
    OTHER_OUTBOUND(299, "其他出库", "other_outbound", OUTBOUND);

    private final int code;
    private final String name;
    private final String value;
    private final InventoryType categories;
}
