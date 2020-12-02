package com.study.annotation;

import org.junit.Test;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 作用描述：参考 https://www.cnblogs.com/heliusKing/p/11372014.html
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2020年12月02日
 */
public class ImportBean01Tests {
    @Test
    public void testImport() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ImportBean01Config01.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }

    @Test
    public void testImportSelector() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ImportBean01Config02.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }

    @Test
    public void testImportAndImportSelector() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ImportBean01Config03.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }

    @Test
    public void testImportAll() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ImportBean01Config04.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }
}

class Chinese {
    // 语文
}

class Mathematics {
    // 数学
}

class English {
    // 英语
}

class Physics {
    // 物理
}

class Chemistry {
    // 化学
}

class Biology {
    // 生物
}

class Politics {
    // 政治
}

class History {
    // 历史
}

class Geography {
    // 地理
}

class Music {
    // 音乐
}

class Art {
    // 美术
}

/**
 * 使用 @Import 注解向容器中导入组件。
 * 向容器中导入组件的方式：通过 xml 配置方式、通过注解(如 @Component 等)、通过 Java 配置类。
 */
@Configuration
@Import(value = {Chinese.class, English.class, Mathematics.class})
// 将指定的三个类导入容器中，@Import 导入组件，id 默认是组件的全类名
class ImportBean01Config01 {
}

/**
 * 自定义逻辑返回需要导入的组件
 */
class MyImportSelector implements ImportSelector {
    /**
     * @param annotationMetadata
     * @return 要导入到容器中的组件的全类名组成的字符串数组
     */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        // 从 selectImports() 方法名就可知道是选择性导入的意思，所以在该方法中可以通过编写不同的逻辑来返回需要导入的组件
        // 本例中自定义的导入选择器只是为了演示，故没有具体的条件判断等，可自行扩展
        return new String[]{"com.study.annotation.Physics", "com.study.annotation.Chemistry", "com.study.annotation.Biology"};
    }
}

@Configuration
@Import(value = {MyImportSelector.class})
class ImportBean01Config02 {
}

class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        // 是否注册了指定类型的 bean
        boolean containsChinese = registry.containsBeanDefinition("com.study.annotation.Chinese");
        boolean containsEnglish = registry.containsBeanDefinition("com.study.annotation.English");
        // 条件判断
        if (!containsChinese && !containsEnglish) {
            // 手动注入 bean
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Mathematics.class);
            registry.registerBeanDefinition("mathematics", rootBeanDefinition);
        }
    }
}

@Configuration
@Import(value = {MyImportBeanDefinitionRegistrar.class})
class ImportBean01Config03 {
}

@Configuration
@Import(value = {Chinese.class, English.class, Mathematics.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
class ImportBean01Config04 {
}