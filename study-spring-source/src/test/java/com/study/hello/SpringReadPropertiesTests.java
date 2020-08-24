package com.study.hello;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
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
    public void classPathResourceTest() {
        System.setProperty("spring.profiles.active", "dev");
        //Resource resource = new ClassPathResource("com/study/hello/hello-properties.xml");
        Resource resource = new ClassPathResource("hello-properties.xml", this.getClass());
        BeanFactory beanFactory = new XmlBeanFactory(resource);
        SpringReadPropertiesClient springReadPropertiesClient = (SpringReadPropertiesClient) beanFactory.getBean("springReadPropertiesClient");
        String content = springReadPropertiesClient.readContent("java.key");
        log.info(content);
        System.out.println(content);
    }

    @Test
    public void inputStreamTest() {
        // 参考 https://www.cnblogs.com/harbin1900/p/9785882.html
        System.setProperty("spring.profiles.active", "dev");
        Resource resource0 = new FileSystemResource("E:/workspace-idea-study/开源框架/spring-framework-5.1.0.RELEASE/study-spring-source/build/resources/test/com/study/hello/hello-properties.xml");

        String path = SpringReadPropertiesTests.class.getResource("/com/study/hello/hello-properties.xml").getPath();
        //String path = this.getClass().getResource("/com/study/hello/hello-properties.xml").getPath();
        //Resource resource1 = new FileSystemResource(new File(path));
        Resource resource1 = new FileSystemResource(path);

        BeanFactory beanFactory = new XmlBeanFactory(resource1);
        SpringReadPropertiesClient springReadPropertiesClient = (SpringReadPropertiesClient) beanFactory.getBean("springReadPropertiesClient");
        String content = springReadPropertiesClient.readContent("java.key");
        log.info(content);
        System.out.println(content);
    }

    @Test
    public void classPathXmlApplicationContextTest() {
        System.setProperty("spring.profiles.active", "dev");
        //BeanFactory beanFactory = new ClassPathXmlApplicationContext(new String[]{"com/study/hello/hello-properties.xml"});
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(new String[]{"hello-properties.xml"}, this.getClass());
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
            result = new String(value.getBytes("ISO-8859-1"), StandardCharsets.UTF_8);
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