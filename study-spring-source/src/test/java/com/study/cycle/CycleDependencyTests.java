package com.study.cycle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 参考 https://blog.csdn.net/qq_41907991/article/details/107164508
 * Spring 解决循环依赖是有前置条件的：
 * 1、出现循环依赖的 Bean 必须要是单例的
 * 2、依赖注入的方式不能全是构造器注入的方式（很多文章说只能解决 setter 方法的循环依赖，这是错误的）
 * <p>
 * 首先，我们要知道 Spring 在创建 Bean 的时候默认是按照自然排序来进行创建的，所以第一步 Spring 会去创建 A。
 * 与此同时，我们应该知道，Spring 在创建 Bean 的过程中分为三步：
 * 1、实例化，对应方法：AbstractAutowireCapableBeanFactory 中的 createBeanInstance() 方法
 * 2、属性注入，对应方法：AbstractAutowireCapableBeanFactory 的 populateBean() 方法
 * 3、初始化，对应方法：AbstractAutowireCapableBeanFactory 的 initializeBean() 方法
 * 这些方法在之前源码分析的文章中都做了详细的解读，如果你之前没看过相关文章，那么你只需要知道：
 * 1、实例化，简单理解就是 new 了一个对象
 * 2、属性注入，为实例化中 new 出来的对象填充属性
 * 3、初始化，执行 aware 接口中的方法，初始化方法，完成 AOP 代理
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2021年03月05日
 */
public class CycleDependencyTests {
    protected static final Log log = LogFactory.getLog(CycleDependencyTests.class);

    @Test
    public void allSetterTest() {
        // A中注入B的方式是通过setter，B中注入A的方式也是通过setter，这个时候的循环依赖是可以被解决
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:com/study/cycle/spring-cycle-setter.xml");
        SetterA setterA = context.getBean(SetterA.class);
        setterA.println();
        SetterB setterB = context.getBean(SetterB.class);
        setterB.println();
    }

    @Test//(expected = BeanCreationException.class)
    public void allConstructorTest() {
        // A中注入B的方式是通过构造器，B中注入A的方式也是通过构造器，这个时候的循环依赖无法被解决
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:com/study/cycle/spring-cycle-constructor.xml");
        ConstructorA constructorA = context.getBean(ConstructorA.class);
        constructorA.println();
        ConstructorB constructorB = context.getBean(ConstructorB.class);
        constructorB.println();
    }

    @Test
    public void setterConstructorTest() {
        // A中注入B的方式是通过setter，B中注入A的方式是通过构造器，这个时候的循环依赖可以被解决。
        // 因为：Spring 在创建 Bean 时默认会根据自然排序进行创建，所以A会先于B进行创建
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:com/study/cycle/spring-cycle-setter-constructor.xml");
        SetterConstructorA setterConstructorA = context.getBean(SetterConstructorA.class);
        setterConstructorA.println();
        SetterConstructorB setterConstructorB = context.getBean(SetterConstructorB.class);
        setterConstructorB.println();
    }

    @Test//(expected = BeanCreationException.class)
    public void constructorSetterTest() {
        // A中注入B的方式是通过构造器，B中注入A的方式是通过setter，这个时候的循环依赖无法被解决。
        // 因为：Spring 在创建 Bean 时默认会根据自然排序进行创建，所以A会先于B进行创建
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:com/study/cycle/spring-cycle-constructor-setter.xml");
        ConstructorSetterA constructorSetterA = context.getBean(ConstructorSetterA.class);
        constructorSetterA.println();
        ConstructorSetterB constructorSetterB = context.getBean(ConstructorSetterB.class);
        constructorSetterB.println();
    }
}

class SetterA {
    private SetterB setterB;

    public void setSetterB(SetterB setterB) {
        this.setterB = setterB;
    }

    public void println() {
        System.out.println("SetterA");
    }
}

class SetterB {
    private SetterA setterA;

    public void setSetterA(SetterA setterA) {
        this.setterA = setterA;
    }

    public void println() {
        System.out.println("SetterB");
    }
}

class ConstructorA {
    private ConstructorB constructorB;

    public ConstructorA(ConstructorB constructorB) {
        this.constructorB = constructorB;
    }

    public void println() {
        System.out.println("ConstructorA");
    }
}


class ConstructorB {
    private ConstructorA constructorA;

    public ConstructorB(ConstructorA constructorA) {
        this.constructorA = constructorA;
    }

    public void println() {
        System.out.println("ConstructorB");
    }
}

class SetterConstructorA {
    private SetterConstructorB setterConstructorB;

    public void setSetterConstructorB(SetterConstructorB setterConstructorB) {
        this.setterConstructorB = setterConstructorB;
    }

    public void println() {
        System.out.println("SetterConstructorA");
    }
}

class SetterConstructorB {
    private SetterConstructorA setterConstructorA;

    public SetterConstructorB(SetterConstructorA setterConstructorA) {
        this.setterConstructorA = setterConstructorA;
    }

    public void println() {
        System.out.println("SetterConstructorB");
    }
}


class ConstructorSetterA {
    private ConstructorSetterB constructorSetterB;

    public ConstructorSetterA(ConstructorSetterB constructorSetterB) {
        this.constructorSetterB = constructorSetterB;
    }

    public void println() {
        System.out.println("ConstructorSetterA");
    }
}

class ConstructorSetterB {
    private ConstructorSetterA constructorSetterA;

    public void setConstructorSetterA(ConstructorSetterA constructorSetterA) {
        this.constructorSetterA = constructorSetterA;
    }

    public void println() {
        System.out.println("ConstructorSetterB");
    }
}