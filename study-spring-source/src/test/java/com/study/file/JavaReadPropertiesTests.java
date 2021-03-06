package com.study.file;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * 作用描述：基于文件形式读取属性文件中的内容(不使用 Spring 框架)
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2020年08月10日
 */
public class JavaReadPropertiesTests {
    protected static final Log log = LogFactory.getLog(JavaReadPropertiesTests.class);

    @Test
    public void readPropertiesByJavaTest01() {
        JavaReadProperties1 javaReadProperties = new JavaReadProperties1("com/study/file/java.properties");
        String content = javaReadProperties.readContent("java.key");
        log.info(content);
        System.out.println(content);
    }

    @Test
    public void readPropertiesByJavaTest02() {
        JavaReadProperties2 javaReadProperties = new JavaReadProperties2("com/study/file/java.properties");
        JavaReadPropertiesClient javaReadPropertiesClient = new JavaReadPropertiesClient(javaReadProperties);
        String content = javaReadPropertiesClient.readContent("java.key");
        log.info(content);
        System.out.println(content);
    }

    @Test
    public void readPropertiesByJavaTest03() {
        JavaReadPropertiesClient javaReadPropertiesClient = JavaReadPropertiesFactory.getReadPropertiesClient("com/study/file/java.properties");
        String content = javaReadPropertiesClient.readContent("java.key");
        log.info(content);
        System.out.println(content);
    }
}


/**
 * 方式一：基于文件形式读取属性文件中的内容
 */
class JavaReadProperties1 {
    protected static final Log log = LogFactory.getLog(JavaReadProperties1.class);

    private String fileName;

    public JavaReadProperties1(String fileName) {
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

/**
 * 方式二：读取文件内容的接口
 */
interface IReadProperties {
    String readContent(String propertiesKey);
}

/**
 * 方式二：基于文件形式读取属性文件中的内容
 */
class JavaReadProperties2 implements IReadProperties {
    protected static final Log log = LogFactory.getLog(JavaReadProperties2.class);

    private String fileName;

    public JavaReadProperties2(String fileName) {
        this.fileName = fileName;
    }

    @Override
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

/**
 * 读取文件内容的客户端
 */
class JavaReadPropertiesClient {
    private IReadProperties readProperties;

    public JavaReadPropertiesClient(IReadProperties readProperties) {
        this.readProperties = readProperties;
    }

    String readContent(String propertiesKey) {
        return readProperties.readContent(propertiesKey);
    }
}

/**
 * 方式三：使用工厂模式
 */
class JavaReadPropertiesFactory {
    public static JavaReadPropertiesClient getReadPropertiesClient(String fileName) {
        JavaReadProperties2 javaReadProperties = new JavaReadProperties2(fileName);
        JavaReadPropertiesClient javaReadPropertiesClient = new JavaReadPropertiesClient(javaReadProperties);
        return javaReadPropertiesClient;
    }
}