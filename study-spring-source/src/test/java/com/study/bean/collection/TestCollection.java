package com.study.bean.collection;

import java.io.Serializable;
import java.util.*;

/**
 * @Description: Spring通过Setter方法注入集合属性
 * @Author: TeGongX
 * @Email: 1135782208@qq.com
 * @Date: 2019年11月23日
 * @Version: V1.0.0
 * @Copyright: Copyright (c) 2019
 */
public class TestCollection implements Serializable {
    private static final long serialVersionUID = -4971758721940221963L;

    /**
     * 注入List集合
     */
    private List<String> list;

    /**
     * 注入Set集合
     */
    private Set<String> set;

    /**
     * 注入Map集合
     */
    private Map<String, Object> map;

    /**
     * 注入Properties集合。注：Properties继承自Hashtable
     */
    private Properties properties;

    /**
     * 注入String数组
     */
    private String[] array;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }

    public void printList() {
        System.out.println("打印List集合：");
        //list.stream().forEach(str -> System.out.println(str));
        list.forEach(System.out::println);

    }

    public void printSet() {
        System.out.println("打印Set集合：");
        //set.stream().forEach(str -> System.out.println(str));
        set.forEach(System.out::println);
    }

    public void printMap() {
        System.out.println("打印Map集合：");
        //map.values().forEach(System.out::println);
        //map.keySet().forEach(k -> System.out.println("key=" + k + "，value=" + map.get(k)));
        //map.entrySet().forEach(entry -> System.out.println("value = " + entry.getKey() + "，value=" + entry.getValue()));
        //map.entrySet().iterator().forEachRemaining(entry -> System.out.println("key=" + entry.getKey() + "，value=" + entry.getValue()));
        map.forEach((k, v) -> System.out.println("key=" + k + "，value=" + v));
    }

    public void printProperties() {
        System.out.println("打印Properties集合：");
        properties.forEach((k, v) -> System.out.println("key=" + k + "，value=" + v));
    }

    public void printArray() {
        System.out.println("打印数组：");
        //Arrays.asList(array).stream().forEach(str -> System.out.println(str));
        Arrays.asList(array).forEach(System.out::println);
    }
}
