package com.study.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 参考 https://www.jianshu.com/p/fc574881e3a2
 */
public class SpringBeanScopeTests {
    private ApplicationContext applicationContext;

    @Before
    public void beforeTest() {
        System.setProperty("spring.profiles.active", "dev");
        System.out.println("==========测试方法开始，即将读取XML配置文件……");
        applicationContext = new ClassPathXmlApplicationContext("com/study/bean/spring-scope-bean.xml");
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
        SingletonBean singletonBean = applicationContext.getBean("singletonBean", SingletonBean.class);
        for (int i = 0; i < 5; i++) {
            singletonBean.print();
        }

        // 每次调用 print() 方法时都会重新从应用上下文获取新的引用，达到了使用原型的目的。
        System.out.println("》》》》》》》》》》实现 ApplicationContextAware 接口");
        SingletonBean1 singletonBean1 = applicationContext.getBean("singletonBean1", SingletonBean1.class);
        for (int i = 0; i < 5; i++) {
            singletonBean1.print();
        }
        System.out.println("》》》》》》》》》》在 XML 中注入相关依赖 Bean");
        SingletonBean11 singletonBean11 = applicationContext.getBean("singletonBean11", SingletonBean11.class);
        //singletonBean11.setApplicationContext(applicationContext);
        for (int i = 0; i < 5; i++) {
            singletonBean11.print();
        }

        // 每次调用 print() 方法时都会重新从应用上下文获取新的引用，达到了使用原型的目的。
        System.out.println("》》》》》》》》》》使用 lookup-method 标签");
        SingletonBean2 singletonBean2 = applicationContext.getBean("singletonBean2", SingletonBean2.class);
        for (int i = 0; i < 5; i++) {
            singletonBean2.print();
        }
    }
}

class PrototypeBean {
    protected static final Log log = LogFactory.getLog(PrototypeBean.class);

    public void sayHello() {
        log.info("【PrototypeBean】Hello ...");
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
class SingletonBean {
    protected static final Log log = LogFactory.getLog(SingletonBean.class);

    private PrototypeBean prototypeBean;

    public PrototypeBean getPrototypeBean() {
        return prototypeBean;
    }

    public void setPrototypeBean(PrototypeBean prototypeBean) {
        this.prototypeBean = prototypeBean;
    }

    public void print() {
        log.info("【SingletonBean】HashCode：" + prototypeBean.hashCode());
        prototypeBean.sayHello();
    }
}

/**
 * 解決方案一
 */
class SingletonBean1 implements ApplicationContextAware {
    protected static final Log log = LogFactory.getLog(SingletonBean1.class);

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void print() {
        PrototypeBean prototypeBean = applicationContext.getBean("prototypeBean", PrototypeBean.class);
        log.info("【SingletonBean1】HashCode：" + prototypeBean.hashCode());
        prototypeBean.sayHello();
    }
}

class SingletonBean11 {
    protected static final Log log = LogFactory.getLog(SingletonBean11.class);

    private ApplicationContext applicationContext;

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void print() {
        PrototypeBean prototypeBean = applicationContext.getBean("prototypeBean", PrototypeBean.class);
        log.info("【SingletonBean11】HashCode：" + prototypeBean.hashCode());
        prototypeBean.sayHello();
    }
}

/**
 * 解決方案二
 * 使用方法注入的方法需要满足语法：<public|protected> [abstract] <return-type> theMethodName(no-arguments);
 */
abstract class SingletonBean2 {
    protected static final Log log = LogFactory.getLog(SingletonBean2.class);

    protected abstract PrototypeBean getPrototypeBean();

    public void print() {
        PrototypeBean prototypeBean = getPrototypeBean();
        log.info("【SingletonBean2】HashCode：" + prototypeBean.hashCode());
        prototypeBean.sayHello();
    }
}