package com.orkva.xmall.inventory.entity.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum InventoryType {
    INBOUND(0, "入库", "inbound", null),
    OUTBOUND(1, "出库", "outbound", null),
    LOCKED(2, "锁定", "locked", OUTBOUND),
    PURCHASE(3, "采购", "purchase", INBOUND),
    RECEDE(4, "退货入库", "recede", INBOUND),
    PICK_UP(5, "提货", "pick_up", OUTBOUND),
    OTHER_INBOUND(98, "其他入库", "other_inbound", INBOUND),
    OTHER_OUTBOUND(99, "其他出库", "other_outbound", OUTBOUND);

    private final int code;
    private final String name;
    private final String value;
    private final InventoryType categories;
}
