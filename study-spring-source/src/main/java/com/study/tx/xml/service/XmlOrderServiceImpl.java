package com.study.tx.xml.service;

import com.study.tx.entity.Order;
import com.study.tx.xml.dao.XmlOrderDao;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
public class XmlOrderServiceImpl implements XmlOrderService {
    private XmlOrderDao xmlOrderDao;
    private XmlOrderItemService xmlOrderItemService;

    public void setXmlOrderDao(XmlOrderDao xmlOrderDao) {
        this.xmlOrderDao = xmlOrderDao;
    }

    public void setXmlOrderItemService(XmlOrderItemService xmlOrderItemService) {
        this.xmlOrderItemService = xmlOrderItemService;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
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
        int result = xmlOrderDao.insert(order);
        System.out.println("操作 t_order 结果：" + result);

        if (order.getOrderId() % 3 == 0) {
            // 模拟异常
            throw new RuntimeException("操作 t_order 之后、 t_order_item 之前抛出了异常！");
        }

        result = xmlOrderItemService.insert(order, productId);

        /*if (orderItem.getOrderId() % 2 == 0) {
            // 模拟异常
            throw new RuntimeException("操作 t_order 之后、 t_order_item 之后抛出了异常！");
        }*/
        return "SUCCESS：" + result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(Order order) {
        return xmlOrderDao.update(order);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Long orderId) {
        return xmlOrderDao.delete(orderId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Order> findByUserId(Long userId) {
        return xmlOrderDao.findByUserId(userId);
    }
}
