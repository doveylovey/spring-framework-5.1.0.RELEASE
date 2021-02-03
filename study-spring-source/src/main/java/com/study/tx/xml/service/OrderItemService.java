package com.study.tx.xml.service;

import com.study.tx.entity.Order;

/**
 * 订单项接口
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2021年02月02日
 */
public interface OrderItemService {
    /**
     * 新增
     *
     * @param order
     * @param productId
     * @return
     */
    int insert(Order order, Long productId);
}
