package com.study.child.bean;

import java.io.Serializable;

/**
 * @Description: 封装、继承、多态是java的面向对象的基本元素，同样，Spring中的bean也可以存在继承关系。
 * 子bean必须与父bean保持兼容，也就是说子bean中必须有父bean定义的所有属性。
 * 父bean必须是抽象bean(即定义abstract="true")或者定义lazy-init="true"，目的是不让bean工厂实例化该bean。
 * @Author: TeGongX
 * @Email: 1135782208@qq.com
 * @Date: 2019年11月19日
 * @Version: V1.0.0
 * @Copyright: Copyright (c) 2019
 */
public class Father implements Serializable {
    private static final long serialVersionUID = -1873866309908582643L;

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Father：name=" + name + ", age=" + age;
    }

    public void sayHello() {
        System.out.println("父bean=====>name：" + name + "，age：" + age);
    }
}
