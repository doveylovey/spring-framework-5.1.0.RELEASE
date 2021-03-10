package com.study.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BeanUtils 测试
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2021年03月09日
 */
public class BeanUtilsTests {
    private List<Student> sourceList = new ArrayList<>(2);
    private Map<Long, Student> sourceMap = new HashMap<>(2);

    @Before
    public void init() {
        sourceList.add(new Student(1L, "张三", LocalDate.of(1994, 10, 20)));
        sourceList.add(new Student(2L, "李四", LocalDate.of(1995, 2, 3)));
        System.out.println("源 List：" + JSON.toJSONString(sourceList));

        sourceMap.put(1L, new Student(1L, "张三", LocalDate.of(1994, 10, 20)));
        sourceMap.put(2L, new Student(2L, "李四", LocalDate.of(1995, 2, 3)));
        System.out.println("源 Map：" + JSON.toJSONString(sourceList));
    }

    @Test
    public void test01() {
        // 使用 org.springframework.beans.BeanUtils.copyProperties() 对 bean 属性进行复制属于浅复制，且不能复制数组、集合等复杂对象
        List<Student> targetList = new ArrayList<>(2);
        BeanUtils.copyProperties(sourceList, targetList);
        System.out.println("目标 List：" + JSON.toJSONString(targetList));

        Map<Long, Student> targetMap = new HashMap<>(2);
        BeanUtils.copyProperties(sourceMap, targetMap);
        System.out.println("目标 Map：" + JSON.toJSONString(targetList));
    }

    @Test
    public void test02() {
        List<Student> targetList = new ArrayList<>(2);
        for (Student src : sourceList) {
            Student des = new Student();
            BeanUtils.copyProperties(src, des);
            targetList.add(des);
        }
        System.out.println("目标 List：" + JSON.toJSONString(targetList));
    }

    @Test
    public void test03() {
        String listJsonString = JSON.toJSONString(sourceList);
        List<Student> targetList = JSON.parseArray(listJsonString, Student.class);
        System.out.println("目标 List：" + JSON.toJSONString(targetList));

        String mapJsonString = JSON.toJSONString(sourceMap);
        Map<Long, Student> targetMap = JSON.parseObject(mapJsonString, Map.class);
        System.out.println("目标 Map：" + JSON.toJSONString(targetMap));
    }

    @Test
    public void test04() {
        // map 转 String
        String map2Str = JSON.toJSONString(sourceMap);
        System.out.println("Map -> String：" + map2Str);
        // String 转 map
        Map<String, String> str2Map = JSONObject.parseObject(map2Str, Map.class);
        System.out.println("String -> Map：" + str2Map);
        // String 转 json
        JSONObject str2Json = JSON.parseObject(map2Str);
        System.out.println("String -> JSON：" + str2Json);
        // json 转 map
        Map<String, String> json2Map = JSONObject.toJavaObject(str2Json, Map.class);
        System.out.println("JSON -> Map：" + json2Map);

    }
}

class Student {
    private Long stuId;
    private String stuName;
    private LocalDate birthday;

    public Student() {
    }

    public Student(Long stuId, String stuName, LocalDate birthday) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.birthday = birthday;
    }

    public Long getStuId() {
        return stuId;
    }

    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "stuId=" + stuId + ", stuName=" + stuName + ", birthday=" + birthday;
    }
}