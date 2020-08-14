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
public class OuterInnerTests {
    private BeanFactory beanFactory;

    @Before
    public void beforeTest() {
        System.setProperty("spring.profiles.active", "dev");
        System.out.println("==========测试方法开始，即将读取XML配置文件……");
        Resource resource = new ClassPathResource("com/study/bean/outer-inner-bean.xml");
        // Spring3.1之前可以使用XmlBeanFactory
        beanFactory = new XmlBeanFactory(resource);
    }

    @After
    public void afterTest() {
        System.out.println("==========测试方法结束");
    }

    @Test
    public void innerBeanTest() {
        System.out.println("【测试】Spring内部Bean");
        // 能获取到外部bean
        Outer outer = beanFactory.getBean("outer", Outer.class);
        System.out.println("外部bean：" + outer);
        outer.sayHello();
        // 不能获取到内部bean，会抛异常
        Inner inner = beanFactory.getBean("inner", Inner.class);
        System.out.println("内部bean：" + inner);
    }
}

/**
 * @Description: 当希望一个bean只被某一个类使用时，就可以使用内部bean。此时内部bean作为类的属性，只能通过该类实例化。
 * @Author: TeGongX
 * @Email: 1135782208@qq.com
 * @Date: 2019年11月19日
 * @Version: V1.0.0
 * @Copyright: Copyright (c) 2019
 */
class Outer implements Serializable {
    private static final long serialVersionUID = -8320854527150084547L;

    private String name;
    private int age;
    /**
     * 内部bean
     */
    private Inner inner;

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

    public Inner getInner() {
        return inner;
    }

    public void setInner(Inner inner) {
        this.inner = inner;
    }

    @Override
    public String toString() {
        return "Outer：name=" + name + ", age=" + age + ", inner=" + inner;
    }

    public void sayHello() {
        System.out.println("Outer=====>name：" + name + "，age:" + age);
        System.out.println("Inner=====>name：" + inner.getName() + "，age:" + inner.getAge());
    }
}

/**
 * @Description: 该类作用描述
 * @Author: TeGongX
 * @Email: 1135782208@qq.com
 * @Date: 2019年11月19日
 * @Version: V1.0.0
 * @Copyright: Copyright (c) 2019
 */
class Inner implements Serializable {
    private static final long serialVersionUID = 7387689802389993288L;

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
        return "Inner： name=" + name + ", age=" + age;
    }
}
