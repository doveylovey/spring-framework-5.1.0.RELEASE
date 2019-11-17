package com.study.model;

import java.io.Serializable;

/**
 * @Description: 参考博文https://www.jianshu.com/p/f501a1496ffd
 * @Author: TeGongX
 * @Email: 1135782208@qq.com
 * @Date: 2019年11月15日
 * @Version: V1.0.0
 * @Copyright: Copyright (c) 2019
 */
public class Person implements Serializable {
    private String name;
    private Integer age;

    public Person() {
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "name=" + name + ", age=" + age;
    }

    public void sayHello() {
        System.out.println("大家好，我叫" + this.getName() + "，今年" + this.getAge() + "岁了。");
    }
}
