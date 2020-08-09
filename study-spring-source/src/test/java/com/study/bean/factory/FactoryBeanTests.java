package com.study.bean.factory;

import com.study.model.Person;
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
 * @Date: 2019年11月17日
 * @Version: V1.0.0
 * @Copyright: Copyright (c) 2019
 */
public class FactoryBeanTests {
    private BeanFactory beanFactory;

    @Before
    public void beforeTest() {
        System.setProperty("spring.profiles.active", "dev");
        System.out.println("==========测试方法开始，即将读取XML配置文件……");
        Resource resource = new ClassPathResource("com/study/bean/factory-bean-person.xml");
        // Spring3.1之前可以使用XmlBeanFactory
        beanFactory = new XmlBeanFactory(resource);
    }

    @After
    public void afterTest() {
        System.out.println("==========测试方法结束");
    }

    @Test
    public void getBeanByTypeTest() {
        System.out.println("【测试】通过类型获取bean");
        PersonService personService = beanFactory.getBean(PersonService.class);
        System.out.println("通过类型获取bean结果：" + personService);
    }

    @Test
    public void getBeanByIdTest() {
        System.out.println("【测试】通过id获取bean");
        // 由于Spring内部做了特殊处理，此时名称为“personFactoryBean”的Bean并不是PersonFactoryBean，而是PersonFactoryBean类中getObject()方法返回的对象。
        // 如果想要获取PersonFactoryBean的对象实例，则可以在Bean的名称前加入“&”(即“&personFactoryBean”)。
        Object personService = beanFactory.getBean("personFactoryBean");
        System.out.println("通过id获取bean结果：" + personService);
        Object factoryBean = beanFactory.getBean("&personFactoryBean");
        System.out.println("如果要获取FactoryBean对象，在id前加&即可：" + factoryBean);
    }

    @Test
    public void personFactoryBeanEnhanceTest() {
        System.out.println("【测试】自定义增强FactoryBean");
        PersonService personService = beanFactory.getBean("personFactoryBeanEnhance", PersonService.class);
        personService.sayHello(new Person("personFactoryBeanEnhance", 18));
        System.out.println("小结：通过输出结果我们可以看到通过FactoryBean接口我们也可以实现BeanFactory中某些接口提供的功能，而且会更加的灵活一些。");
    }
}
