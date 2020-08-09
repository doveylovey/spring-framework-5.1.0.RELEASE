package com.study.bean.child;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @Description: 该类作用描述
 * @Author: TeGongX
 * @Email: 1135782208@qq.com
 * @Date: 2019年11月19日
 * @Version: V1.0.0
 * @Copyright: Copyright (c) 2019
 */
public class FatherSonTests {
    private BeanFactory beanFactory;

    @Before
    public void beforeTest() {
        System.setProperty("spring.profiles.active", "dev");
        System.out.println("==========测试方法开始，即将读取XML配置文件……");
        Resource resource = new ClassPathResource("com.study.bean/father-son-bean.xml");
        // Spring3.1之前可以使用XmlBeanFactory
        beanFactory = new XmlBeanFactory(resource);
    }

    @After
    public void afterTest() {
        System.out.println("==========测试方法结束");
    }

    @Test
    public void innerBeanTest() {
        System.out.println("【测试】Spring父子Bean");
        // 能获取到子bean
        Son son = beanFactory.getBean("son", Son.class);
        System.out.println("子bean：" + son);
        son.sayHello();
        // 不能获取到父bean，会抛异常
        Father father = beanFactory.getBean("father", Father.class);
        System.out.println("父bean：" + father);
    }
}
