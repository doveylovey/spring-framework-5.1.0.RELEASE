package com.study.bean.child;

import java.io.Serializable;

/**
 * @Description: 子bean和父bean之间无继承关系，而是通过配置文件维护其父子关系的
 * @Author: TeGongX
 * @Email: 1135782208@qq.com
 * @Date: 2019年11月19日
 * @Version: V1.0.0
 * @Copyright: Copyright (c) 2019
 */
public class Son implements Serializable {
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
        return "Son：name=" + name + ", age=" + age;
    }

    public void sayHello() {
        System.out.println("子bean=====>name：" + name + "，age：" + age);
    }
}
