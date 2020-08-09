package com.study.hello;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * 作用描述：使用 Spring 框架读取属性文件中的内容
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2020年08月10日
 */
public class SpringReadPropertiesTests {
    protected static final Log log = LogFactory.getLog(JavaReadPropertiesTests.class);

    @Test
    public void beforeAdviceTest() {
        System.setProperty("spring.profiles.active", "dev");
        Resource resource = new ClassPathResource("com/study/hello/hello-properties.xml");
        // Spring3.1之前可以使用XmlBeanFactory
        BeanFactory beanFactory = new XmlBeanFactory(resource);
        SpringReadPropertiesClient springReadPropertiesClient = (SpringReadPropertiesClient) beanFactory.getBean("springReadPropertiesClient");
        String content = springReadPropertiesClient.readContent("java.key");
        log.info(content);
        System.out.println(content);
    }
}

class SpringReadProperties {
    protected static final Log log = LogFactory.getLog(SpringReadProperties.class);

    private String fileName;

    public SpringReadProperties(String fileName) {
        this.fileName = fileName;
    }

    public String readContent(String propertiesKey) {
        String result = "";
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            properties.load(inputStream);
            String value = properties.getProperty(propertiesKey, "指定键 " + propertiesKey + " 没有配置，这是默认值！");
            result = new String(value.getBytes(), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            log.error(ex);
            ex.printStackTrace();
        }
        return result;
    }
}

class SpringReadPropertiesClient {
    private SpringReadProperties springReadProperties;

    public SpringReadPropertiesClient(SpringReadProperties springReadProperties) {
        this.springReadProperties = springReadProperties;
    }

    String readContent(String propertiesKey) {
        return springReadProperties.readContent(propertiesKey);
    }
}