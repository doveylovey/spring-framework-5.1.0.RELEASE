package com.study.tx;

import com.study.tx.annotation.service.AnnotationOrderService;
import com.study.tx.config.SpringTxConfig;
import com.study.tx.config.service.ConfigOrderService;
import com.study.tx.xml.service.XmlOrderService;
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
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring-tx-xml.xml");
        XmlOrderService orderService = context.getBean(XmlOrderService.class);
        String result = orderService.insert(1L, 1L);
        System.out.println("操作结果：" + result);
    }

    @Test
    public void txAnnotationTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring-tx-annotation.xml");
        AnnotationOrderService orderService = context.getBean(AnnotationOrderService.class);
        String result = orderService.insert(1L, 1L);
        System.out.println("操作结果：" + result);
    }

    @Test
    public void txConfigTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringTxConfig.class);
        ConfigOrderService orderService = context.getBean(ConfigOrderService.class);
        String result = orderService.insert(1L, 1L);
        System.out.println("操作结果：" + result);
    }
}
