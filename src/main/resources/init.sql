CREATE DATABASE xmall_inventory CHARSET utf8mb4 COLLATE utf8mb4_general_ci;

DROP TABLE IF EXISTS `tb_batches_inventories`;
CREATE TABLE `tb_batches_inventories`
(
    `id`           BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `lot_number`   VARCHAR(255) UNIQUE                 NOT NULL,
    `sku_id`       BIGINT UNSIGNED                     NOT NULL,
    `total`        INT       DEFAULT 0                 NOT NULL,
    `remaining`    INT       DEFAULT 0                 NOT NULL,
    `outbound`     INT       DEFAULT 0                 NOT NULL,
    `available`    INT       DEFAULT 0                 NOT NULL,
    `locked`       INT       DEFAULT 0                 NOT NULL,
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;

DROP VIEW IF EXISTS `vw_sku_inventories`;
CREATE VIEW `vw_sku_inventories` AS
SELECT ROW_NUMBER() OVER (ORDER BY `xmall_inventory`.`tb_batches_inventories`.`sku_id`) AS id,
       `xmall_inventory`.`tb_batches_inventories`.`sku_id`                              AS `sku_id`,
       SUM(`xmall_inventory`.`tb_batches_inventories`.`total`)                          AS `total`,
       SUM(`xmall_inventory`.`tb_batches_inventories`.`remaining`)                      AS `remaining`,
       SUM(`xmall_inventory`.`tb_batches_inventories`.`outbound`)                       AS `outbound`,
       SUM(`xmall_inventory`.`tb_batches_inventories`.`available`)                      AS `available`,
       SUM(`xmall_inventory`.`tb_batches_inventories`.`locked`)                         AS `locked`
FROM `xmall_inventory`.`tb_batches_inventories`
GROUP BY `xmall_inventory`.`tb_batches_inventories`.`sku_id`;

DROP TABLE IF EXISTS `tb_batches_inventories_change_logs`;
CREATE TABLE `tb_batches_inventories_change_logs`
(
    `id`                BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `lot_number`        VARCHAR(255)                        NOT NULL,
    `sku_id`            BIGINT UNSIGNED                     NOT NULL,
    `inventory_type`    VARCHAR(255)                        NOT NULL,
    `total`             INT       DEFAULT 0                 NOT NULL,
    `total_changes`     INT       DEFAULT 0                 NOT NULL,
    `remaining`         INT       DEFAULT 0                 NOT NULL,
    `remaining_changes` INT       DEFAULT 0                 NOT NULL,
    `outbound`          INT       DEFAULT 0                 NOT NULL,
    `outbound_changes`  INT       DEFAULT 0                 NOT NULL,
    `available`         INT       DEFAULT 0                 NOT NULL,
    `available_changes` INT       DEFAULT 0                 NOT NULL,
    `locked`            INT       DEFAULT 0                 NOT NULL,
    `locked_changes`    INT       DEFAULT 0                 NOT NULL,
    `created_date`      TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `updated_date`      TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    INDEX `idx_lot_number` (`lot_number`)
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;