package com.study.tx.config.dao;

import com.study.tx.entity.OrderItem;

/**
 * 订单项 DAO 接口
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2021年02月02日
 */
public interface ConfigOrderItemDao {
    /**
     * 新增
     *
     * @param orderItem
     * @return
     */
    int insert(OrderItem orderItem);
}
