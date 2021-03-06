package com.study.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 作用描述：TODO
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2021年03月06日
 */
@Controller
public class TestController {
    private final Log log = LogFactory.getLog(TestController.class);
    @RequestMapping(value = {"/", "/index"})
    public String index() {
        log.info("调用 index()，跳转到 index.jsp 页面");
        return "index";
    }

    @RequestMapping("/hello")
    public String hello() {
        log.info("调用 hello()，跳转到 hello.jsp 页面");
        return "hello";
    }

    @ResponseBody
    @RequestMapping("/say/{name}")
    public String sayHello(@PathVariable("name") String name) {
        log.info("调用 sayHello()，输出 json 字符串");
        return "hello, " + name;
    }
}
