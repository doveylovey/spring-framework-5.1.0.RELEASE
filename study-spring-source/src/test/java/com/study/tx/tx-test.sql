
CREATE TABLE `t_order`
(
    `order_id`     bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单编号',
    `user_id`      bigint(20) unsigned NOT NULL COMMENT '用户编号',
    `order_status` tinyint(4) unsigned NOT NULL COMMENT '订单状态：1-待支付、2-支付成功、3-支付失败、4-待发货、5-待收货、6-待评价、7-已完成',
    `pay_type`     tinyint(4) unsigned NOT NULL COMMENT '支付方式：1-支付宝、2-微信、3-银行卡',
    `pay_money`    decimal(10, 2)      NOT NULL COMMENT '支付金额',
    `gmt_create`   datetime            NOT NULL COMMENT '创建时间',
    `gmt_update`   datetime            NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`order_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE `t_order_item`
(
    `item_id`        bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单项编号',
    `order_id`       bigint(20) unsigned NOT NULL COMMENT '订单编号',
    `user_id`        bigint(20) unsigned NOT NULL COMMENT '用户编号',
    `product_id`     bigint(20) unsigned NOT NULL COMMENT '商品编号',
    `product_name`   varchar(255)        NOT NULL COMMENT '商品名称',
    `product_price`  decimal(10, 2)      NOT NULL COMMENT '商品单价',
    `purchase_count` int(11)             NOT NULL COMMENT '购买数量',
    `pay_money`      decimal(10, 2)      NOT NULL COMMENT '支付金额',
    PRIMARY KEY (`item_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

