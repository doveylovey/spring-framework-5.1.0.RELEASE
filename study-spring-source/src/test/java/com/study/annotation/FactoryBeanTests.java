package com.study.annotation;

import org.junit.Test;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 参考：https://www.cnblogs.com/jing-daye/p/5911138.html、https://www.cnblogs.com/shamo89/p/9911473.html
 */
public class FactoryBeanTests {
    @Test
    public void testFactoryBeanWithAnnotation() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        System.out.println("调用 getBean(\"myFactoryBean\") 的结果：" + ac.getBean("myFactoryBean"));
        System.out.println("调用 getBean(\"&myFactoryBean\") 的结果：" + ac.getBean("&myFactoryBean"));
    }
}

@Component
class MyFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        System.out.println("执行了一段复杂的创建 Bean 的逻辑");
        return new TestBean();
    }

    @Override
    public Class<?> getObjectType() {
        return TestBean.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}

class TestBean {
    public TestBean() {
        System.out.println("TestBean 被创建出来了");
    }
}

@Configuration
@ComponentScan("com.study.annotation")
class ApplicationConfig {
}