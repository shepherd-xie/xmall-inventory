INSERT INTO xmall_inventory.tb_batches_inventories(id, lot_number, sku_id, total, remaining, outbound, available,
                                                   `locked`,
                                                   created_date, updated_date)
VALUES (1, 122, 1, 40, 40, 0, 40, 0, '2023-08-07 06:18:26', '2023-08-07 06:18:26'),
       (2, 123, 1, 20, 20, 0, 20, 0, '2023-08-07 06:18:26', '2023-08-07 06:18:26'),
       (3, 121, 2, 35, 35, 0, 35, 0, '2023-08-07 06:18:26', '2023-08-07 06:18:26'),
       (4, 120, 3, 20, 15, 5, 10, 5, '2023-08-07 06:18:26', '2023-08-07 06:18:26'),
       (5, 124, 2, 25, 10, 15, 0, 10, '2023-08-07 06:18:26', '2023-08-07 06:18:26');

INSERT INTO xmall_inventory.tb_batches_inventories_change_logs(id, lot_Number, sku_id, inventory_type, total,
                                                               total_changes, remaining, remaining_changes, outbound,
                                                               outbound_changes, available, available_changes, `locked`,
                                                               locked_changes, created_date, updated_date)
VALUES (1, 122, 1, 'PURCHASE', 40, 40, 40, 40, 0, 0, 40, 40, 0, 0, current_timestamp, current_timestamp),
       (2, 121, 2, 'PURCHASE', 35, 35, 35, 35, 0, 0, 35, 35, 0, 0, current_timestamp, current_timestamp),
       (3, 123, 1, 'PURCHASE', 20, 20, 20, 20, 0, 0, 20, 20, 0, 0, current_timestamp, current_timestamp),
       (4, 120, 3, 'PURCHASE', 20, 20, 20, 20, 0, 0, 20, 20, 0, 0, current_timestamp, current_timestamp),
       (5, 124, 2, 'PURCHASE', 25, 25, 25, 25, 0, 0, 25, 25, 0, 0, current_timestamp, current_timestamp),
       (6, 124, 2, 'LOCKED', 25, 0, 25, 0, 0, 0, 0, -25, 25, 25, current_timestamp, current_timestamp),
       (7, 124, 2, 'PICK_UP', 25, 0, 10, -15, 15, 15, 0, 0, 10, -15, current_timestamp, current_timestamp),
       (8, 120, 3, 'LOCKED', 20, 0, 20, 0, 0, 0, 10, -10, 10, 10, current_timestamp, current_timestamp),
       (9, 120, 3, 'PICK_UP', 20, 0, 15, -5, 5, 5, 10, 0, 5, -5, current_timestamp, current_timestamp);