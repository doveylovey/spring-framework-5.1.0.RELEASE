package com.study.tx.xml.service;

import com.study.tx.entity.Order;
import com.study.tx.entity.OrderItem;
import com.study.tx.xml.dao.OrderItemDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 订单项接口的实现
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2021年02月02日
 */
public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemDao orderItemDao;

    public void setOrderItemDao(OrderItemDao orderItemDao) {
        this.orderItemDao = orderItemDao;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public int insert(Order order, Long productId) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(order.getOrderId());
        orderItem.setUserId(order.getUserId());
        orderItem.setProductId(productId);
        orderItem.setProductName("商品名称-" + productId);
        orderItem.setProductPrice(BigDecimal.ONE);
        orderItem.setPurchaseCount(10);
        orderItem.setPayMoney(BigDecimal.TEN);

        /*if (orderItem.getOrderId() % 2 == 0) {
            // 模拟异常
            throw new RuntimeException("操作 t_order_item 之前抛出了异常！");
        }*/

        int result = orderItemDao.insert(orderItem);
        System.out.println("操作 t_order_item 结果：" + result);

        if (orderItem.getOrderId() % 2 == 0) {
            // 模拟异常
            throw new RuntimeException("操作 t_order_item 之后抛出了异常！");
        }

        return result;
    }
}
