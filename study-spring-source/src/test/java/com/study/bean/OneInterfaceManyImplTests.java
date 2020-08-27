package com.study.bean;

import com.study.ioc.IocImplTests;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 作用描述：TODO
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2020年08月27日
 */
public class OneInterfaceManyImplTests {
    @Test
    public void defaultListableBeanFactoryTest01() {
        System.setProperty("spring.profiles.active", "dev");
        Resource resource = new ClassPathResource("com/study/bean/one-intf-many-impl.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        PersonService personService = factory.getBean("teacherService", PersonService.class);
        personService.tellName("teacherService");
        PersonService studentService = factory.getBean("studentService", PersonService.class);
        studentService.tellName("studentService");
    }
}

interface PersonService {
    void tellName(String name);
}

class TeacherServiceImpl implements PersonService {
    protected static final Log log = LogFactory.getLog(IocImplTests.class);

    @Override
    public void tellName(String name) {
        log.info("TeacherServiceImpl=====>" + name);
        System.out.println("TeacherServiceImpl=====>" + name);
    }
}

class StudentServiceImpl implements PersonService {
    protected static final Log log = LogFactory.getLog(IocImplTests.class);

    @Override
    public void tellName(String name) {
        log.info("StudentServiceImpl=====>" + name);
        System.out.println("StudentServiceImpl=====>" + name);
    }
}