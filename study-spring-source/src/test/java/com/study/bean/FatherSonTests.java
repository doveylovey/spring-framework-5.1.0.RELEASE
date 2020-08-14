package com.study.bean;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.Serializable;

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
        Resource resource = new ClassPathResource("com/study/bean/father-son-bean.xml");
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

/**
 * @Description: 封装、继承、多态是java的面向对象的基本元素，同样，Spring中的bean也可以存在继承关系。
 * 子bean必须与父bean保持兼容，也就是说子bean中必须有父bean定义的所有属性。
 * 父bean必须是抽象bean(即定义abstract="true")或者定义lazy-init="true"，目的是不让bean工厂实例化该bean。
 * @Author: TeGongX
 * @Email: 1135782208@qq.com
 * @Date: 2019年11月19日
 * @Version: V1.0.0
 * @Copyright: Copyright (c) 2019
 */
class Father implements Serializable {
    private static final long serialVersionUID = -1873866309908582643L;

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Father：name=" + name + ", age=" + age;
    }

    public void sayHello() {
        System.out.println("父bean=====>name：" + name + "，age：" + age);
    }
}

/**
 * @Description: 子bean和父bean之间无继承关系，而是通过配置文件维护其父子关系的
 * @Author: TeGongX
 * @Email: 1135782208@qq.com
 * @Date: 2019年11月19日
 * @Version: V1.0.0
 * @Copyright: Copyright (c) 2019
 */
class Son implements Serializable {
    private static final long serialVersionUID = -1873866309908582643L;

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Son：name=" + name + ", age=" + age;
    }

    public void sayHello() {
        System.out.println("子bean=====>name：" + name + "，age：" + age);
    }
}