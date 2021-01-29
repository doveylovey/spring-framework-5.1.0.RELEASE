package com.study.event;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * Spring 事件机制
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2021年1月29日
 */
public class SpringEventTests {
    @Test
    public void testSpringEvent() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(EventConfig.class);
        DemoPublisher demoPublisher = applicationContext.getBean("demoPublisher", DemoPublisher.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (int i = 1; i < 11; i++) {
            demoPublisher.publishEvent("当前线程：" + Thread.currentThread().getName()
                    + "，当前时间：" + LocalDateTime.now().format(formatter)
                    + "，=》 " + i);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        applicationContext.close();
    }
}

@Configuration
@ComponentScan(basePackages = "com.study.event")
class EventConfig {
}

/**
 * 定义一个事件，继承自 ApplicationEvent 并且写相应的构造函数
 */
class DemoApplicationEvent extends ApplicationEvent {
    private String message;

    public DemoApplicationEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

/**
 * 定义一个事件监听者，实现 ApplicationListener 接口，重写 onApplicationEvent() 方法
 */
@Component
class DemoApplicationListener implements ApplicationListener<DemoApplicationEvent> {
    /**
     * 使用 onApplicationEvent 接收消息
     */
    @Override
    public void onApplicationEvent(DemoApplicationEvent event) {
        String message = event.getMessage();
        System.err.println("【事件监听者接收消息】" + message);
    }
}

/**
 * 发布事件，可以通过 ApplicationEventPublisher 的 publishEvent() 方法发布消息
 */
@Component
class DemoPublisher {
    @Resource
    private ApplicationContext applicationContext;

    public void publishEvent(String message) {
        System.out.println("【事件发布者发布消息】" + message);
        DemoApplicationEvent event = new DemoApplicationEvent(this, message);
        applicationContext.publishEvent(event);
    }
}