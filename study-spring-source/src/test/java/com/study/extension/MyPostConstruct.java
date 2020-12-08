package com.study.extension;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 作用描述：@PostConstruct 在具体 Bean 的实例化过程中执行，@PostConstruct 注解的方法会在构造方法之后执行，
 * 顺序为：Constructor > @Autowired > @PostConstruct > 静态方法，所以这个注解就避免了一些需要在构造方法
 * 里使用依赖组件的尴尬(与之对应的还有 @PreDestroy，在对象消亡之前执行)。使用特点如下：
 * 1、只有一个非静态方法能使用此注解
 * 2、被注解的方法不得有任何参数
 * 3、被注解的方法返回值必须为 void
 * 4、被注解方法不得抛出已检查异常
 * 5、此方法只会被执行一次
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2020年12月08日
 */
@Component
public class MyPostConstruct {
    public MyPostConstruct() {
        System.out.println("自定义 PostConstruct 的构造方法");
    }

    @PostConstruct
    public void postConstruct() {
        // 被 @PostConstruct 修饰的方法会在服务器加载 Servlet 的时候运行，
        // 并且只会被服务器调用一次，类似于 Servlet 的 init() 方法。
        // 被 @PostConstruct 修饰的方法会在构造函数之后，init() 方法之前运行。
        System.out.println("自定义 PostConstruct 的 postConstruct() 方法");
    }

    @PreDestroy
    public void preDestroy() {
        // 被 @PreDestroy 修饰的方法会在服务器卸载 Servlet 的时候运行，
        // 并且只会被服务器调用一次，类似于 Servlet 的 destroy() 方法。
        // 被 @PreDestroy 修饰的方法会在 destroy() 方法之后运行，在 Servlet 被彻底卸载之前。
        System.out.println("自定义 PostConstruct 的 preDestroy() 方法");
    }

    public void initMethod() {
        System.out.println("自定义 PostConstruct 的 initMethod() 方法");
    }
}
