package com.study.extension;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 作用描述：TODO
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2020年12月07日
 */
@Component
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("自定义 ApplicationListener 的 onApplicationEvent() 方法");
        ApplicationContext applicationContext = event.getApplicationContext();
        int beanCount = applicationContext.getBeanDefinitionCount();
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        System.out.println("自定义 ApplicationListener.onApplicationEvent() =====> Bean 数量：" + beanCount + "，名称:" + Arrays.asList(beanNames));
    }
}
