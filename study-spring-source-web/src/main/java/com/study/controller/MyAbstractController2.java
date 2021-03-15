package com.study.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 作用描述：自定义 Controller 直接通过 response 写响应
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2021年03月15日
 */
public class MyAbstractController2 extends AbstractController {
    private final Log log = LogFactory.getLog(MyAbstractController2.class);

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("进入了自定义的 AbstractController 实现类2……");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("继承自 AbstractController 的自定义 Controller");
        // 如果想直接在该控制器中写响应，则可以通过返回 null 告诉 DispatcherServlet 自己已经写出响应了，不需要它进行视图解析
        return null;
    }
}
