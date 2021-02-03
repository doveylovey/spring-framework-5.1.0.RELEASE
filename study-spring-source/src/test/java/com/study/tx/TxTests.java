package com.study.tx;

import com.study.tx.config.SystemConfig;
import com.study.tx.annotation.service.OrderService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring 事务分析
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2021年02月02日
 */
public class TxTests {
    protected static final Log log = LogFactory.getLog(TxTests.class);

    @Test
    public void txXmlAopTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-context-tx-xml-aop.xml");
        OrderService orderService = context.getBean(OrderService.class);
        //OrderItemService orderItemService = context.getBean(OrderItemService.class);

        String result = orderService.insert(1L, 1L);
        System.out.println("操作结果：" + result);
    }

    @Test
    public void txAnnotationTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-context-tx-annotation.xml");
        OrderService orderService = context.getBean(OrderService.class);
        //OrderItemService orderItemService = context.getBean(OrderItemService.class);

        String result = orderService.insert(1L, 1L);
        System.out.println("操作结果：" + result);
    }

    @Test
    public void txConfigTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SystemConfig.class);
        OrderService orderService = context.getBean(OrderService.class);
        //OrderItemService orderItemService = context.getBean(OrderItemService.class);

        String result = orderService.insert(1L, 1L);
        System.out.println("操作结果：" + result);
    }
}
