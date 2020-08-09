package com.study.bean.inner;

import java.io.Serializable;

/**
 * @Description: 当希望一个bean只被某一个类使用时，就可以使用内部bean。此时内部bean作为类的属性，只能通过该类实例化。
 * @Author: TeGongX
 * @Email: 1135782208@qq.com
 * @Date: 2019年11月19日
 * @Version: V1.0.0
 * @Copyright: Copyright (c) 2019
 */
public class Outer implements Serializable {
    private static final long serialVersionUID = -8320854527150084547L;

    private String name;
    private int age;
    /**
     * 内部bean
     */
    private Inner inner;

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

    public Inner getInner() {
        return inner;
    }

    public void setInner(Inner inner) {
        this.inner = inner;
    }

    @Override
    public String toString() {
        return "Outer：name=" + name + ", age=" + age + ", inner=" + inner;
    }

    public void sayHello() {
        System.out.println("Outer=====>name：" + name + "，age:" + age);
        System.out.println("Inner=====>name：" + inner.getName() + "，age:" + inner.getAge());
    }
}
