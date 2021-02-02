package com.study.tx.mapper;

import com.study.tx.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单 Mapper 接口
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2021年02月02日
 */
public interface OrderMapper {
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
    int delete(@Param("orderId") Long orderId);

    /**
     * 查询
     *
     * @param userId
     * @return
     */
    List<Order> findByUserId(@Param("userId") Long userId);
}
