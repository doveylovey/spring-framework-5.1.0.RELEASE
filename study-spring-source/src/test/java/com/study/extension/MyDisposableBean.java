package com.study.extension;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 作用描述：TODO
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2020年12月08日
 */
@Component
public class MyDisposableBean implements DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("自定义 DisposableBean 的 destroy() 方法");
    }

    public MyDisposableBean() {
        System.out.println("自定义 DisposableBean 的构造方法");
    }

    @PostConstruct
    public void postConstruct() {
        // 被 @PostConstruct 修饰的方法会在服务器加载 Servlet 的时候运行，
        // 并且只会被服务器调用一次，类似于 Servlet 的 init() 方法。
        // 被 @PostConstruct 修饰的方法会在构造函数之后，init() 方法之前运行。
        System.out.println("自定义 DisposableBean 的 postConstruct() 方法");
    }

    @PreDestroy
    public void preDestroy() {
        // 被 @PreDestroy 修饰的方法会在服务器卸载 Servlet 的时候运行，
        // 并且只会被服务器调用一次，类似于 Servlet 的 destroy() 方法。
        // 被 @PreDestroy 修饰的方法会在 destroy() 方法之后运行，在 Servlet 被彻底卸载之前。
        System.out.println("自定义 DisposableBean 的 preDestroy() 方法");
    }
}
