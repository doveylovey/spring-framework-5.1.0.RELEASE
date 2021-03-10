package com.study.util;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class DeepBeanUtils {
    public static <T> List copyList(List<T> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList();
        }
        String jsonString = JSON.toJSONString(list);
        return JSON.parseArray(jsonString, list.get(0).getClass());
    }

    public static Map<String, Object> copyMap(Map map) {
        return JSON.parseObject(JSON.toJSONString(map));
    }
}
