package com.study.tx.service;

import com.study.tx.entity.Order;
import com.study.tx.entity.OrderItem;

import java.util.List;

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

    /**
     * 修改
     *
     * @param orderItem
     * @return
     */
    int update(OrderItem orderItem);

    /**
     * 删除
     *
     * @param itemId
     * @return
     */
    int delete(Long itemId);

    /**
     * 查询
     *
     * @param orderId
     * @return
     */
    List<OrderItem> findByOrderId(Long orderId);
}
