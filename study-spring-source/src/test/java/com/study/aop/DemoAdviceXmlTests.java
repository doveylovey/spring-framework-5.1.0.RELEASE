package com.study.aop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 作用描述：https://cloud.tencent.com/developer/article/1531317
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2020年08月09日
 */
public class DemoAdviceXmlTests {
    private BeanFactory beanFactory;
    private IDemoAdviceService demoAdviceService;

    @Before
    public void beforeTest() {
        System.setProperty("spring.profiles.active", "dev");
        System.out.println("==========测试方法开始，即将读取XML配置文件……");
        Resource resource = new ClassPathResource("com/study/aop/demo-advice-bean.xml");
        // Spring3.1之前可以使用XmlBeanFactory
        beanFactory = new XmlBeanFactory(resource);
        demoAdviceService = (IDemoAdviceService) beanFactory.getBean("demoAdviceAop");
    }

    @After
    public void afterTest() {
        System.out.println("==========测试方法结束");
    }

    @Test
    public void beforeAdviceTest() {
        System.out.println("【方法调用结果】" + demoAdviceService.demoBefore("before"));
    }

    @Test
    public void afterAdviceTest() {
        System.out.println("【方法调用结果】" + demoAdviceService.demoAfter("after"));
    }

    @Test
    public void throwsAdviceTest() {
        System.out.println("【方法调用结果】" + demoAdviceService.demoThrows("throws"));
    }

    @Test
    public void aroundAdviceTest() {
        System.out.println("【方法调用结果】" + demoAdviceService.demoAround("around"));
    }
}
