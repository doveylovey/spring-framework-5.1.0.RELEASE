package com.study.bean;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringValueResolver;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 作用描述：Spring Bean 生命周期示例
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2020年12月08日
 */
public class BeanLifeTests {
    @Test
    public void testExtension() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SuperBeanLifeConfig.class);
        SuperClass superClass = applicationContext.getBean("superClass", SuperClass.class);
        System.out.println(superClass.getParam());
        applicationContext.close();

        applicationContext = new AnnotationConfigApplicationContext(SubBeanLifeConfig.class);
        SubClass subClass = applicationContext.getBean("subClass", SubClass.class);
        System.out.println(subClass.getParam());
        applicationContext.registerShutdownHook();
    }
}

@Configuration
class SuperBeanLifeConfig {
    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public SuperClass superClass() {
        SuperClass superClass = new SuperClass();
        superClass.setParam("SuperClass param");
        return superClass;
    }

    @Bean
    public MyBeanPostProcessor myBeanPostProcessor() {
        return new MyBeanPostProcessor();
    }
}

@Configuration
class SubBeanLifeConfig {
    @Bean
    public MyBeanPostProcessor myBeanPostProcessor() {
        return new MyBeanPostProcessor();
    }

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public SubClass subClass() {
        SubClass subClass = new SubClass();
        subClass.setParam("SubClass param");
        return subClass;
    }
}

class SuperClass implements BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, InitializingBean, DisposableBean {
    private String param;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        System.out.println("SuperClass 属性的 set() 方法");
        this.param = param;
    }

    public SuperClass() {
        System.out.println("SuperClass 的构造方法");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("SuperClass 的 setBeanFactory() 方法");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("SuperClass 的 setBeanName() 方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("SuperClass 的 destroy() 方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("SuperClass 的 afterPropertiesSet() 方法");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("SuperClass 的 setApplicationContext() 方法");
    }

    @PostConstruct
    public void postConstruct() {
        // 自定义初始化方法
        System.out.println("SuperClass 的 postConstruct() 方法");
    }

    public void initMethod() {
        System.out.println("SuperClass 的 initMethod() 方法");
    }

    @PreDestroy
    public void preDestroy() {
        // 自定义销毁方法
        System.out.println("SuperClass 的 preDestroy() 方法");
    }

    public void destroyMethod() {
        System.out.println("SuperClass 的 destroyMethod() 方法");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("SuperClass 的 finalize() 方法");
    }
}

class MyBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof SuperClass) {
            // 容器加载的时候会加载一些其他的 bean，会调用初始化前和初始化后方法，这里只关注 SuperClass 的生命周期
            System.out.println("MyBeanPostProcessor 的 postProcessBeforeInitialization() 方法");
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof SuperClass) {
            // 容器加载的时候会加载一些其他的 bean，会调用初始化前和初始化后方法，这里只关注 SuperClass 的生命周期
            System.out.println("MyBeanPostProcessor 的 postProcessAfterInitialization() 方法");
        }
        return bean;
    }
}

class SubClass extends SuperClass implements BeanClassLoaderAware, EnvironmentAware,
        EmbeddedValueResolverAware, ResourceLoaderAware, ApplicationEventPublisherAware, MessageSourceAware {
    private String param;

    @Override
    public String getParam() {
        return param;
    }

    @Override
    public void setParam(String param) {
        System.out.println("SubClass 属性的 set() 方法");
        this.param = param;
    }

    public SubClass() {
        System.out.println("SubClass 的构造方法");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("SubClass 的 setBeanClassLoader() 方法");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("SubClass 的 setApplicationEventPublisher() 方法");
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        System.out.println("SubClass 的 setEmbeddedValueResolver() 方法");
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("SubClass 的 setEnvironment() 方法");
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        System.out.println("SubClass 的 setMessageSource() 方法");
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("SubClass 的 setResourceLoader() 方法");
    }
}