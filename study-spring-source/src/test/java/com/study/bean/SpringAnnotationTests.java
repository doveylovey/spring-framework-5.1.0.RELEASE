package com.study.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 参考 https://blog.csdn.net/gavin_john/article/details/81051343
 */
public class SpringAnnotationTests {
    private ApplicationContext applicationContext;

    @Before
    public void beforeTest() {
        System.setProperty("spring.profiles.active", "dev");
        System.out.println("==========测试方法开始，即将扫描 com.study 包及其子包下的注解……");
        applicationContext = new AnnotationConfigApplicationContext("com.study");
    }

    @After
    public void afterTest() {
        System.out.println("==========测试方法结束");
    }

    @Test
    public void test01() {
        System.out.println("【测试】单例 Bean 和原型 Bean 的调用");

        // 每次输出 PrototypeBean 的 HashCode 都是一样的，证明实际上并没有达到使用原型 bean 的目的。
        System.out.println("》》》》》》》》》》没有使用到原型 bean");
        AnnotationSingletonBean annotationSingletonBean = applicationContext.getBean("annotationSingletonBean", AnnotationSingletonBean.class);
        for (int i = 0; i < 5; i++) {
            annotationSingletonBean.print();
        }

        // 每次调用 print() 方法时都会重新从应用上下文获取新的引用，达到了使用原型的目的。
        System.out.println("》》》》》》》》》》使用 @Autowired 注解");
        AnnotationSingletonBean1 annotationSingletonBean1 = applicationContext.getBean("annotationSingletonBean1", AnnotationSingletonBean1.class);
        for (int i = 0; i < 5; i++) {
            annotationSingletonBean1.print();
        }
        System.out.println("》》》》》》》》》》实现 ApplicationContextAware 接口");
        AnnotationSingletonBean11 annotationSingletonBean11 = applicationContext.getBean("annotationSingletonBean11", AnnotationSingletonBean11.class);
        for (int i = 0; i < 5; i++) {
            annotationSingletonBean11.print();
        }

        // 每次调用 print() 方法时都会重新从应用上下文获取新的引用，达到了使用原型的目的。
        System.out.println("》》》》》》》》》》使用 @Lookup 注解");
        AnnotationSingletonBean2 annotationSingletonBean2 = applicationContext.getBean("annotationSingletonBean2", AnnotationSingletonBean2.class);
        for (int i = 0; i < 5; i++) {
            annotationSingletonBean2.print();
        }
    }
}

@Component
@Scope("prototype")
class AnnotationPrototypeBean {
    protected static final Log log = LogFactory.getLog(AnnotationPrototypeBean.class);

    public void sayHello() {
        log.info("【AnnotationPrototypeBean】Hello ...");
    }
}

/**
 * 情景分析：
 * 在 Spring 的诸多应用场景中 bean 都是单例形式，当一个单例 bean 需要和一个非单例 bean 组合使用或者
 * 一个非单例 bean 需要和另一个非单例 bean 组合使用时，我们通常将依赖以属性的方式放到 bean 中来引用，
 * 然后以 @Autowired 来标记需要注入的属性，但这种方式在 bean 的生命周期不同时将会出现很明显的问题。
 * 假设单例 beanA 需要一个非单例 beanB(原型)，我们在 beanA 中注入 beanB，每次调用 beanA 中的方法时都会用到 beanB，
 * 我们知道 Spring IOC 容器只在容器初始化时执行一次，也就是 beanA 中的依赖 beanB 只有一次注入的机会，
 * 但实际上我们需要每次调用 beanB 中的方法时都获取一个新的对象(原型)，所以问题明显就是：
 * 我们需要 beanB 是一个原型 bean，而事实上 beanB 的依赖只注入了一次变成了事实上的单例 bean。
 * <p>
 * 解决方案：
 * 方法一：在 beanA 中引入 ApplicationContext 每次调用方法时用上下文的 getBean(name, class) 方法去重新获取 beanB 的实例。
 * 方法二：使用 @Lookup 注解。
 * 这两种解决方案都能解决我们遇到的问题，但是第二种相对而言更简单。
 */
@Component
@Scope("singleton")
class AnnotationSingletonBean {
    protected static final Log log = LogFactory.getLog(AnnotationSingletonBean.class);

    @Autowired
    private AnnotationPrototypeBean annotationPrototypeBean;

    public void print() {
        log.info("【AnnotationSingletonBean】HashCode：" + annotationPrototypeBean.hashCode());
        annotationPrototypeBean.sayHello();
    }
}

/**
 * 解決方案一
 */
@Component
@Scope("singleton")
class AnnotationSingletonBean1 {
    protected static final Log log = LogFactory.getLog(AnnotationSingletonBean1.class);

    @Autowired
    private ApplicationContext applicationContext;

    public void print() {
        AnnotationPrototypeBean annotationPrototypeBean = applicationContext.getBean("annotationPrototypeBean", AnnotationPrototypeBean.class);
        log.info("【AnnotationSingletonBean1】HashCode：" + annotationPrototypeBean.hashCode());
        annotationPrototypeBean.sayHello();
    }
}

@Component
@Scope("singleton")
class AnnotationSingletonBean11 implements ApplicationContextAware {
    protected static final Log log = LogFactory.getLog(AnnotationSingletonBean11.class);

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void print() {
        AnnotationPrototypeBean annotationPrototypeBean = applicationContext.getBean("annotationPrototypeBean", AnnotationPrototypeBean.class);
        log.info("【AnnotationSingletonBean11】HashCode：" + annotationPrototypeBean.hashCode());
        annotationPrototypeBean.sayHello();
    }
}

/**
 * 解決方案二
 * 使用方法注入的方法需要满足语法：<public|protected> [abstract] <return-type> theMethodName(no-arguments);
 */
@Component
@Scope("singleton")
abstract class AnnotationSingletonBean2 {
    protected static final Log log = LogFactory.getLog(AnnotationSingletonBean2.class);

    @Lookup
    protected abstract AnnotationPrototypeBean getAnnotationPrototypeBean();

    public void print() {
        AnnotationPrototypeBean annotationPrototypeBean = getAnnotationPrototypeBean();
        log.info("【AnnotationSingletonBean2】HashCode：" + annotationPrototypeBean.hashCode());
        annotationPrototypeBean.sayHello();
    }
}