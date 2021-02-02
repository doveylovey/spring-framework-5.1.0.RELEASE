package com.study.tx.service;

import com.github.pagehelper.PageHelper;
import com.study.tx.entity.Order;
import com.study.tx.entity.OrderItem;
import com.study.tx.mapper.OrderItemMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单项接口的实现
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2021年02月02日
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Resource
    private OrderItemMapper orderItemMapper;

    @Transactional(rollbackFor = Exception.class)
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

        if (orderItem.getOrderId() % 2 == 0) {
            // 模拟异常
            throw new RuntimeException("操作 t_order_item 之前抛出了异常！");
        }

        int result = orderItemMapper.insert(orderItem);
        System.out.println("操作 t_order_item 结果：" + result);

        /*if (orderItem.getOrderId() % 2 == 0) {
            // 模拟异常
            throw new RuntimeException("操作 t_order_item 之后抛出了异常！");
        }*/

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(OrderItem orderItem) {
        return orderItemMapper.update(orderItem);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Long itemId) {
        return orderItemMapper.delete(itemId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderItem> findByOrderId(Long orderId) {
        PageHelper.startPage(1, 10);
        return orderItemMapper.findByOrderId(orderId);
    }
}
