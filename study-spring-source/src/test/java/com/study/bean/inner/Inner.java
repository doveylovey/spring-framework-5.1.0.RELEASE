package com.study.bean.inner;

import java.io.Serializable;

/**
 * @Description: 该类作用描述
 * @Author: TeGongX
 * @Email: 1135782208@qq.com
 * @Date: 2019年11月19日
 * @Version: V1.0.0
 * @Copyright: Copyright (c) 2019
 */
public class Inner implements Serializable {
    private static final long serialVersionUID = 7387689802389993288L;

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
        return "Inner： name=" + name + ", age=" + age;
    }
}
