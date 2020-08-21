package com.study.bean;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class InheritTests {
    private BeanFactory beanFactory;

    @Before
    public void beforeTest() {
        System.setProperty("spring.profiles.active", "dev");
        System.out.println("==========测试方法开始，即将读取XML配置文件……");
        Resource resource = new ClassPathResource("com/study/bean/inherit-bean.xml");
        // Spring3.1之前可以使用XmlBeanFactory
        beanFactory = new XmlBeanFactory(resource);
    }

    @After
    public void afterTest() {
        System.out.println("==========测试方法结束");
    }

    @Test
    public void inheritBeanTest() {
        System.out.println("【测试】Spring继承Bean");
        SubclassA subclassA = beanFactory.getBean(SubclassA.class);
        System.out.println("子类A：" + subclassA);
        BaseClass baseClass = beanFactory.getBean(BaseClass.class);
        System.out.println("基类：" + baseClass);
    }
}

class BaseClass {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BaseClass{ name=" + name + '}';
    }
}

class SubclassA extends BaseClass {
    @Override
    public String toString() {
        return "SubclassA{ name=" + super.getName() + '}';
    }
}
