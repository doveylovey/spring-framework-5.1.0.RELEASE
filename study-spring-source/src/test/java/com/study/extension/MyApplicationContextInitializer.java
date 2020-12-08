package com.study.extension;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

/**
 * 作用描述：该类在 SpringBoot 项目中的使用非常简单，但在纯 Spring 项目中就需要结合 @RunWith(SpringJUnit4ClassRunner.class)
 * 和 @ContextConfiguration(initializers = MyApplicationContextInitializer.class) 一起做测试使用
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2020年12月07日
 */
public class MyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("自定义 ApplicationContextInitializer 的 initialize() 方法");
        int beanCount = applicationContext.getBeanDefinitionCount();
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        System.out.println("自定义 ApplicationContextInitializer.initialize() =====> Bean 数量：" + beanCount + "，名称:" + Arrays.asList(beanNames));
    }
}


