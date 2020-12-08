package com.study.extension;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 作用描述：InitializingBean 是 Spring 提供的一个接口，只包含一个方法 afterPropertiesSet()。
 * 凡是实现了该接口的类，当其对应的 Bean 交由 Spring 管理后，当其必要的属性全部设置完成后，
 * Spring 会调用该 Bean 的 afterPropertiesSet()。在 Bean 在实例化的过程中执执行顺序为：
 * Constructor > @PostConstruct > InitializingBean > init-method
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2020年12月08日
 */
@Component
public class MyInitializingBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("自定义 InitializingBean 的 afterPropertiesSet() 方法");
    }

    public MyInitializingBean() {
        System.out.println("自定义 InitializingBean 的构造方法");
    }

    @PostConstruct
    public void postConstruct() {
        // 被 @PostConstruct 修饰的方法会在服务器加载 Servlet 的时候运行，
        // 并且只会被服务器调用一次，类似于 Servlet 的 init() 方法。
        // 被 @PostConstruct 修饰的方法会在构造函数之后，init() 方法之前运行。
        System.out.println("自定义 InitializingBean 的 postConstruct() 方法");
    }

    @PreDestroy
    public void preDestroy() {
        // 被 @PreDestroy 修饰的方法会在服务器卸载 Servlet 的时候运行，
        // 并且只会被服务器调用一次，类似于 Servlet 的 destroy() 方法。
        // 被 @PreDestroy 修饰的方法会在 destroy() 方法之后运行，在 Servlet 被彻底卸载之前。
        System.out.println("自定义 InitializingBean 的 preDestroy() 方法");
    }

    public void initMethod() {
        System.out.println("自定义 InitializingBean 的 initMethod() 方法");
    }
}
