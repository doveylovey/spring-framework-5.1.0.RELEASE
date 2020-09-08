package com.study.ioc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * 作用描述：编程式使用IOC容器
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2020年08月25日R
 */
public class IocImplTests {
    protected static final Log log = LogFactory.getLog(IocImplTests.class);

    /**
     * 注：在实际应用中很少使用这种原始的方式，但其对理解IOC容器的工作原理是很有帮助的，因为编程式使用容器的过程可以清楚地
     * 揭示IOC容器实现中那些关键类(如：Resource、DefaultListableBeanFactory、XmlBeanDefinitionReader)之间的
     * 相互关系(如：它们是如何把IOC容器的功能解耦的、它们是如何结合在一起为IOC容器服务的等)。
     * <p>
     * 编程式使用IOC容器的步骤：
     * 1、创建IOC配置文件的抽象资源，这个抽象资源包含了BeanDefinition的定义信息。
     * 2、创建一个BeanFactory，这里使用的是DefaultListableBeanFactory。
     * 3、创建一个载入BeanDefinition的读取器，这里使用XmlBeanDefinitionReader来载入XML文件形式的BeanDefinition，通过一个回调配置给BeanFactory。
     * 4、从定义好的资源位置读入配置信息，具体的解析过程由XmlBeanDefinitionReader来完成。完成整个载入和注册Bean定义之后，需要的IOC容器就建立起来了，这时IOC容器就可以直接使用了。
     * <p>
     * IOC 容器的初始化包括三个过程：BeanDefinition 的 Resource 定位、载入、注册。
     * 1、BeanDefinition 的资源定位由 ResourceLoader 通过统一的 Resource 接口来完成，这个 Resource 对各种形式的 BeanDefinition 的使用提供了统一接口，常见的 BeanDefinition 存在形式：文件系统中(使用 FileSystemResource 加载)、类路径下(使用 ClassPathResource 加载)。
     * 2、BeanDefinition 的载入就是把用户定义好的 Bean 表示成 IOC 容器内部的数据结构，而这个数据结构就是 BeanDefinition。总的来说，BeanDefinition 实际上就是 POJO 对象在 IOC 容器中的抽象，它定义了一系列的数据来使得 IOC 容器能够方便的对 POJO 对象(也就是 Spring 的 Bean)进行管理，即 BeanDefinition 就是 Spring 的领域对象。
     * 3、向 IOC 容器注册这些 BeanDefinition 就是通过调用 BeanDefinitionRegistry 接口的实现来完成的，这个注册过程把载入过程中解析得到的 BeanDefinition 向 IOC 容器进行注册。可以看到，在 IOC 容器内部是通过一个 HashMap 来持有这些 BeanDefinition 数据的。
     */
    @Test
    public void defaultListableBeanFactoryTest01() {
        // 指定使用 dev 的配置文件。可以试试不指定该属性的效果？
        System.setProperty("spring.profiles.active", "dev");
        // step1、创建IOC配置文件的抽象资源，这个抽象资源包含了BeanDefinition的定义信息
        //ClassPathResource resource = new ClassPathResource("com/study/ioc/ioc-bean.xml");
        ClassPathResource resource = new ClassPathResource("ioc-bean.xml", this.getClass());
        // step2、创建一个BeanFactory，这里使用DefaultListableBeanFactory
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // step3、创建一个载入BeanDefinition的读取器，这里使用XmlBeanDefinitionReader来载入XML文件形式的BeanDefinition
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        // step4、从定义好的资源位置读入配置信息，具体的解析过程由XmlBeanDefinitionReader来完成
        reader.loadBeanDefinitions(resource);
        // 完成整个载入和注册Bean定义之后，需要的IOC容器就建立起来了，这时IOC容器就可以直接使用了
        IocBean iocBean = factory.getBean("iocBean", IocBean.class);
        String result = iocBean.toString();
        log.info(result);
        System.out.println(result);
    }
}
