package com.study.web;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * 作用描述：TODO
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2020年12月20日
 */
public class RestTemplateTests {
    @Test
    public void test01() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate.getForEntity("http://www.baidu.com", String.class);
        System.out.println(entity.getStatusCode());
        System.out.println(entity.getBody());
    }
}
