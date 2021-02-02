package com.study.tx.mapper;

import com.study.tx.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单项 Mapper 接口
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2021年02月02日
 */
public interface OrderItemMapper {
    /**
     * 新增
     *
     * @param orderItem
     * @return
     */
    int insert(OrderItem orderItem);

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
    int delete(@Param("itemId") Long itemId);

    /**
     * 查询
     *
     * @param orderId
     * @return
     */
    List<OrderItem> findByOrderId(@Param("orderId") Long orderId);
}
