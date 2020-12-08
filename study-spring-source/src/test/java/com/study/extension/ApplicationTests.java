package com.study.extension;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 作用描述：TODO
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2020年12月08日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(initializers = MyApplicationContextInitializer.class)
public class ApplicationTests {
    @Test
    public void testExtension() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExtensionConfig.class);
        //applicationContext.close();
    }
}

@Configuration
@ComponentScan(basePackages = "com.study.extension")
class ExtensionConfig {
}

class MyRegisterBean {
    // 用于测试的 Bean
}