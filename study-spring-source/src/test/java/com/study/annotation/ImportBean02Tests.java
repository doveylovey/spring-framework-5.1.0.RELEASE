package com.study.annotation;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

/**
 * 作用描述：TODO
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2020年12月02日
 */
public class ImportBean02Tests {
    @Test
    public void testImport() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ImportBean02Config01.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }

    @Test
    public void testImportResource() {
        System.setProperty("spring.profiles.active", "dev");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ImportBean02Config02.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }
}

class Monday {
    // 星期一：英文缩写 Mon
}

class Tuesday {
    // 星期二：英文缩写 Tue
}

class Wednesday {
    //星期三：英文缩写 Wed
}

class Thursday {
    //星期四：英文缩写 Thur
}

class Friday {
    //星期五：英文缩写 Fri
}

class Saturday {
    //星期六：英文缩写 Sat
}

class Sunday {
    //星期日：英文缩写 Sun
}

@Configuration
class ConfigA {
    @Bean
    public Monday getMonday() {
        return new Monday();
    }

    @Bean
    public Tuesday getTuesday() {
        return new Tuesday();
    }
}

@Configuration
class ConfigB {
    @Bean(name = "wednesday")
    public Wednesday getWednesday() {
        return new Wednesday();
    }

    @Bean(name = "thursday")
    public Thursday getThursday() {
        return new Thursday();
    }
}

@Configuration
@Import(value = {ConfigA.class, ConfigB.class})
class ImportBean02Config01 {
}

@Configuration
@ImportResource(locations = {"classpath:com/study/annotation/demo-annotation-bean.xml"})
class ImportBean02Config02 {
}