package com.study.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description: 通过FactoryBean创建一个代理类来增强目标类
 * @Author: TeGongX
 * @Email: 1135782208@qq.com
 * @Date: 2019年11月17日
 * @Version: V1.0.0
 * @Copyright: Copyright (c) 2019
 */
public class PersonFactoryBeanEnhance implements FactoryBean, InitializingBean, DisposableBean {
    private Object proxyObject;
    private Object targetObject;
    private String interfaceName;

    public Object getProxyObject() {
        return proxyObject;
    }

    public void setProxyObject(Object proxyObject) {
        this.proxyObject = proxyObject;
    }

    public Object getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public PersonService getObject1() throws Exception {
        System.out.println("UserService实例化之前……");
        PersonService personService = new PersonServiceImpl();
        System.out.println("UserService实例化之后……");
        return personService;
    }

    public Class<?> getObjectType1() {
        return PersonService.class;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("对象销毁的回调方法destroy()……");
    }

    @Override
    public Object getObject() throws Exception {
        return proxyObject;
    }

    @Override
    public Class<?> getObjectType() {
        return proxyObject.getClass() == null ? Object.class : proxyObject.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    /**
     * PersonFactoryBeanEnhance对象实例化的方法
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行afterPropertiesSet()……");
        proxyObject = Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[]{Class.forName(interfaceName)},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("代理方法执行开始……");
                        Object invoke = method.invoke(targetObject, args);
                        System.out.println("代理方法执行结束……");
                        return invoke;
                    }
                }
        );
    }
}
