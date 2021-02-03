package com.study.tx.annotation.dao;

import com.study.tx.entity.Order;

import java.util.List;

/**
 * 订单 DAO 接口
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2021年02月02日
 */
public interface OrderDao {
    /**
     * 新增
     *
     * @param order
     * @return
     */
    int insert(Order order);

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

    Integer userOrderNum(Long userId);

    List<Integer> userOrderIds(Long userId);

    Order findOne(Long orderId);

    List<Order> findPage(Long userId, Integer pageNum, Integer pageSize);
}
