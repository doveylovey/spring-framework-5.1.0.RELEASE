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
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
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
    public void readPropertiesFromClasspath() throws IOException {
        // 第一种
        ClassPathResource classPathResource = new ClassPathResource("com/study/hello/java.properties");
        InputStream inputStream1 = classPathResource.getInputStream();

        // 第二种
        InputStream inputStream2 = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/study/hello/java.properties");

        // 第三种
        //InputStream inputStream3 = this.getClass().getResourceAsStream("/com/study/hello/java.properties");
        InputStream inputStream3 = SpringReadPropertiesTests.class.getResourceAsStream("/com/study/hello/java.properties");

        //第四种
        File file = ResourceUtils.getFile("classpath:com/study/hello/java.properties");
        InputStream inputStream4 = new FileInputStream(file);

        Properties properties = new Properties();
        properties.load(inputStream4);
        String propertiesKey = "java.key";
        String value = properties.getProperty(propertiesKey, "指定键 " + propertiesKey + " 没有配置，这是默认值！");
        String result = new String(value.getBytes("ISO-8859-1"), StandardCharsets.UTF_8);
        log.info(result);
        System.out.println(result);
    }

    @Test
    public void getProjectPathOrFilePathTest() {
        // 参考 https://www.cnblogs.com/convict/p/11330449.html
        // 写法一：结果为 file:/E:/xxx 的形式
        String url1 = SpringReadPropertiesTests.class.getClassLoader().getResource("").toString();
        log.info("【写法一】项目根路径：url1=" + url1);
        String url2 = SpringReadPropertiesTests.class.getResource("/").toString();
        log.info("【写法一】项目根路径：url2=" + url2);
        String url3 = SpringReadPropertiesTests.class.getResource("").toString();
        log.info("【写法一】文件根路径：url3=" + url3);
        // 写法二：调用 getResource() 方法后再调用 getFile() 方法，这样就没有 file: 了，而是 /E:/xxx 的形式
        String url4 = SpringReadPropertiesTests.class.getClassLoader().getResource("").getFile();
        log.info("【写法二】项目根路径：url4=" + url4);
        String url5 = SpringReadPropertiesTests.class.getResource("/").getFile();
        log.info("【写法二】项目根路径：url5=" + url5);
        String url6 = SpringReadPropertiesTests.class.getResource("").getFile();
        log.info("【写法二】文件根路径：url6=" + url6);
        // 写法三：调用 getResource() 方法后再调用 getPath() 方法，这样就没有 file: 了，而是 /E:/xxx 的形式
        String url7 = SpringReadPropertiesTests.class.getClassLoader().getResource("").getPath();
        log.info("【写法三】项目根路径：url7=" + url7);
        String url8 = SpringReadPropertiesTests.class.getResource("/").getPath();
        log.info("【写法三】项目根路径：url8=" + url8);
        String url9 = SpringReadPropertiesTests.class.getResource("").getPath();
        log.info("【写法三】文件根路径：url9=" + url9);
    }

    @Test
    public void fileSystemResourceTest() {
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
    public void classPathResourceTest() {
        System.setProperty("spring.profiles.active", "dev");
        // Resource是Spring用来封装IO处理的类，假如BeanDefinition信息是以xml文件形式存在的，
        // 那么就可以使用ClassPathResource来构造需要的Resource，然后作为参数传递给XmlBeanFactory的构造函数。
        // 这样IOC容器就可以方便的定位到需要的BeanDefinition信息来对Bean完成容器的初始化和依赖注入过程。
        //Resource resource = new ClassPathResource("com/study/hello/hello-properties.xml");
        Resource resource = new ClassPathResource("hello-properties.xml", this.getClass());
        BeanFactory beanFactory = new XmlBeanFactory(resource);
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