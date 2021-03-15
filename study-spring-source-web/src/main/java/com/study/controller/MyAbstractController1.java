package com.study.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 作用描述：自定义 Controller 返回 ModelAndView 对象
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2021年03月15日
 */
public class MyAbstractController1 extends AbstractController {
    private final Log log = LogFactory.getLog(MyAbstractController1.class);

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("进入了自定义的 AbstractController 实现类1……");
        // ModelAndView 对象中包括了要返回的逻辑视图以及数据模型
        ModelAndView mav = new ModelAndView();
        String name = request.getParameter("name");
        // 设置视图名称，可以是字符串，也可以是视图对象
        mav.setViewName("page/abstract");
        // 设置数据模型
        mav.addObject("name", name);
        return mav;
    }
}
