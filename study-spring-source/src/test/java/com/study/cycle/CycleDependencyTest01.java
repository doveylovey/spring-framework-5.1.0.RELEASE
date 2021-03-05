package com.study.cycle;

import com.study.tx.xml.service.XmlOrderService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CycleDependencyTest01 {
    protected static final Log log = LogFactory.getLog(CycleDependencyTest01.class);

    @Test
    public void txXmlAopTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:com/study/tx/spring-tx-xml.xml");
        XmlOrderService orderService = context.getBean(XmlOrderService.class);
        String result = orderService.insert(1L, 1L);
        System.out.println("操作结果：" + result);
    }
}
