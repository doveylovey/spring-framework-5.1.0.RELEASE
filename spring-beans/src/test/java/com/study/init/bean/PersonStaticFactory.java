package com.study.init.bean;

import com.study.model.Person;

/**
 * @Description: 静态工厂方法实例化
 * @Author: TeGongX
 * @Email: 1135782208@qq.com
 * @Date: 2019年11月16日
 * @Version: V1.0.0
 * @Copyright: Copyright (c) 2019
 */
public class PersonStaticFactory {
    public static Person newInstance(String name, Integer age) {
        return new Person(name, age);
    }
}