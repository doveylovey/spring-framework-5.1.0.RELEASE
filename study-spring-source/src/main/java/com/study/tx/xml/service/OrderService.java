package com.study.tx.xml.service;

import com.study.tx.entity.Order;

import java.util.List;

/**
 * 订单接口
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2021年02月02日
 */
public interface OrderService {
    /**
     * 新增
     *
     * @param userId
     * @param productId
     * @return
     */
    String insert(Long userId, Long productId);

    /**
     * 修改
     *
     * @param order
     * @return
     */
    int update(Order order);

    /**
     * 删除
     *
     * @param orderId
     * @return
     */
    int delete(Long orderId);

    /**
     * 查询
     *
     * @param userId
     * @return
     */
    List<Order> findByUserId(Long userId);
}
