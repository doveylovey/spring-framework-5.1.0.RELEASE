package com.study.extension;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 作用描述：BeanFactoryPostProcessor 是 BeanFactory 的后置处理器，在 BeanFactory 标准初始化之后调用，
 * 来定制和修改 BeanFactory 的内容；此时所有的 Bean 定义已经保存加载到 beanFactory，但 Bean 实例还未创建。
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2020年12月08日
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("自定义 BeanFactoryPostProcessor 的 postProcessBeanFactory() 方法");
        int beanCount = beanFactory.getBeanDefinitionCount();
        String[] beanNames = beanFactory.getBeanDefinitionNames();
        System.out.println("自定义 BeanFactoryPostProcessor.postProcessBeanFactory() =====> Bean 数量：" + beanCount + "，名称:" + Arrays.asList(beanNames));
    }
}
