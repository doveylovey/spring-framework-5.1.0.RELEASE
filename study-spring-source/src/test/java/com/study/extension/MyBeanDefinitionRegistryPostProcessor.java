package com.study.extension;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 作用描述：BeanDefinitionRegistryPostProcessor 继承于 BeanFactoryPostProcessor，但优先于 BeanFactoryPostProcessor 执行，
 * 可以利用 BeanDefinitionRegistryPostProcessor 给容器中再额外添加一些组件
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2020年12月08日
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("自定义 BeanDefinitionRegistryPostProcessor 的 postProcessBeanDefinitionRegistry() 方法");
        // BeanDefinitionRegistry 是 Bean 定义信息的保存中心，以后 BeanFactory 就是按照 BeanDefinitionRegistry 里面保存的每一个 Bean 定义信息创建 Bean 实例
        int beanCount = registry.getBeanDefinitionCount();
        String[] beanNames = registry.getBeanDefinitionNames();
        System.out.println("自定义 BeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry() =====> Bean 数量：" + beanCount + "，名称:" + Arrays.asList(beanNames));
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(MyRegisterBean.class).getBeanDefinition();
        registry.registerBeanDefinition("myRegisterBean", beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("自定义 BeanDefinitionRegistryPostProcessor 的 postProcessBeanFactory() 方法");
        int beanCount = beanFactory.getBeanDefinitionCount();
        String[] beanNames = beanFactory.getBeanDefinitionNames();
        System.out.println("自定义 BeanDefinitionRegistryPostProcessor.postProcessBeanFactory() =====> Bean 数量：" + beanCount + "，名称:" + Arrays.asList(beanNames));
    }
}