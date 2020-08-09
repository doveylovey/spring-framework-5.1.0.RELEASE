package com.study.bean.factory;

import com.study.model.Person;

/**
 * @Description: 该类作用描述
 * @Author: TeGongX
 * @Email: 1135782208@qq.com
 * @Date: 2019年11月17日
 * @Version: V1.0.0
 * @Copyright: Copyright (c) 2019
 */
public class PersonServiceImpl implements PersonService {
    public PersonServiceImpl() {
        System.out.println("被实例化了……");
    }

    @Override
    public void sayHello(Person person) {
        System.out.println("方法被执行了：hello " + person.toString());
    }
}
