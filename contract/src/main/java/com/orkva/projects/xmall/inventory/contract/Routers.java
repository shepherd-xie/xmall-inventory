package com.orkva.projects.xmall.inventory.contract;

/**
 * Routers
 *
 * @author Shepherd Xie
 * @version 2023/8/21
 */
public interface Routers {

    interface Inventory {

        String LIST_BATCHES_INVENTORIES = "/inventories/batches";

        String PAGE_BATCHES_INVENTORIES = "/inventories/batches/page";

        String LIST_AVAILABLE_BATCHES_INVENTORIES = "/inventories/batches/available";

        String LIST_BATCHES_INVENTORY_CHANGE_LOGS = "/inventories/batches/changes";

        String LIST_SKU_INVENTORIES = "/inventories/sku";

        String PURCHASE = "/inventories/purchase";

        String LOCKED = "/inventories/locked";

        String PICKUP = "/inventories/pickup";

        String RECEDE = "/inventories/recede";

    }

}
