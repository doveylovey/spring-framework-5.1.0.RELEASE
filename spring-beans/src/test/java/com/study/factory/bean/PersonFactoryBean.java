package com.study.factory.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @Description: 自定义简单FactoryBean
 * @Author: TeGongX
 * @Email: 1135782208@qq.com
 * @Date: 2019年11月17日
 * @Version: V1.0.0
 * @Copyright: Copyright (c) 2019
 */
public class PersonFactoryBean implements FactoryBean<PersonService> {
    @Override
    public PersonService getObject() throws Exception {
        System.out.println("UserService实例化之前……");
        PersonService personService = new PersonServiceImpl();
        System.out.println("UserService实例化之后……");
        return personService;
    }

    @Override
    public Class<?> getObjectType() {
        return PersonService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
