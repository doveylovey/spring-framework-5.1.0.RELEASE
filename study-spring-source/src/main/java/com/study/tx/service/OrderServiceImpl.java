package com.study.tx.service;

import com.github.pagehelper.PageHelper;
import com.study.tx.entity.Order;
import com.study.tx.entity.OrderItem;
import com.study.tx.mapper.OrderItemMapper;
import com.study.tx.mapper.OrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单接口的实现
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2021年02月02日
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderItemService orderItemService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String insert(Long userId, Long productId) {
        LocalDateTime now = LocalDateTime.now();
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderStatus(1);
        order.setPayType(1);
        order.setPayMoney(BigDecimal.TEN);
        order.setGmtCreate(now);
        order.setGmtUpdate(now);
        int result = orderMapper.insert(order);
        System.out.println("操作 t_order 结果：" + result);

        if (order.getOrderId() % 3 == 0) {
            // 模拟异常
            throw new RuntimeException("操作 t_order 之后、 t_order_item 之前抛出了异常！");
        }

        result = orderItemService.insert(order, productId);

        /*if (orderItem.getOrderId() % 2 == 0) {
            // 模拟异常
            throw new RuntimeException("操作 t_order 之后、 t_order_item 之后抛出了异常！");
        }*/
        return "SUCCESS：" + result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(Order order) {
        return orderMapper.update(order);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Long orderId) {
        return orderMapper.delete(orderId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Order> findByUserId(Long userId) {
        PageHelper.startPage(1, 10);
        return orderMapper.findByUserId(userId);
    }
}
