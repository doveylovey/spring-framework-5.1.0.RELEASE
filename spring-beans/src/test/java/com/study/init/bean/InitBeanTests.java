package com.study.init.bean;

import com.study.model.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @Description: 该类作用描述
 * @Author: TeGongX
 * @Email: 1135782208@qq.com
 * @Date: 2019年11月16日
 * @Version: V1.0.0
 * @Copyright: Copyright (c) 2019
 */
public class InitBeanTests {
    private BeanFactory beanFactory;
    private DefaultListableBeanFactory listableBeanFactory;

    //@Before
    public void beforeTest1() {
        System.setProperty("spring.profiles.active", "dev");
        System.out.println("==========测试方法开始，即将读取XML配置文件……");
        Resource resource = new ClassPathResource("com.study/init-bean-person.xml");
        // Spring3.1之前可以使用XmlBeanFactory
        beanFactory = new XmlBeanFactory(resource);
    }

    @Before
    public void beforeTest2() {
        System.setProperty("spring.profiles.active", "dev");
        System.out.println("==========测试方法开始，即将读取XML配置文件……");
        // 创建IOC配置文件的抽象资源
        Resource resource = new ClassPathResource("com.study/init-bean-person.xml");
        // 创建一个BeanFactory
        listableBeanFactory = new DefaultListableBeanFactory();
        // 把读取配置信息的BeanDefinitionReader配置给BeanFactory
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(listableBeanFactory);
        // 从定义好的资源位置读入配置信息，具体的解析过程由XmlBeanDefinitionReader来完成
        reader.loadBeanDefinitions(resource);
    }

    @After
    public void afterTest() {
        System.out.println("==========测试方法结束");
    }

    // ******* 以下测试方法使用beanFactory或listableBeanFactory均可以
    @Test
    public void noConstructorTest() {
        System.out.println("【测试】使用无参数的构造函数实例化bean");
        Person noConstructor = listableBeanFactory.getBean("noConstructor", Person.class);
        noConstructor.sayHello();
    }

    @Test
    public void nameConstructorTest() {
        System.out.println("【测试】使用带参数的构造函数实例化bean：通过name属性指定参数名称来注入属性");
        Person noConstructor = listableBeanFactory.getBean("nameConstructor", Person.class);
        noConstructor.sayHello();
    }

    @Test
    public void indexConstructorTest() {
        System.out.println("【测试】使用带参数的构造函数实例化bean：通过index属性指定构造器中参数的位置来注入属性");
        Person noConstructor = listableBeanFactory.getBean("indexConstructor", Person.class);
        noConstructor.sayHello();
    }

    @Test
    public void factoryInstanceTest() {
        System.out.println("【测试】使用工厂方法实例化bean");
        Person noConstructor = listableBeanFactory.getBean("factoryInstance", Person.class);
        noConstructor.sayHello();
    }

    @Test
    public void staticFactoryInstanceTest() {
        System.out.println("【测试】使用静态工厂方法实例化bean");
        Person noConstructor = listableBeanFactory.getBean("staticFactoryInstance", Person.class);
        noConstructor.sayHello();
    }
}
