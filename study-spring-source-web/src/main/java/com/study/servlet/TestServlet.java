package com.study.servlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 加载顺序(从启动日志来看)：
 * 启动的顺序为 listener -> filter -> servlet。简单记为：理(Listener)发(Filter)师(Servlet)
 * 执行的顺序不会因为三个标签在配置文件中的先后顺序而改变。
 *
 * @author Administrator
 */
public class TestServlet extends HttpServlet {
    private static final long serialVersionUID = -4263672728918819141L;
    private final Log logger = LogFactory.getLog(TestServlet.class);

    @Override
    public void init() throws ServletException {
        logger.info("TestServlet init()......");
        super.init();
    }

    @Override
    public void destroy() {
        logger.info("TestServlet destroy()......");
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("TestServlet doPost() start......");
        // 操作 attribute
        request.setAttribute("a", "a");
        request.setAttribute("a", "b");
        request.getAttribute("a");
        request.removeAttribute("a");
        // 操作 session
        request.getSession().setAttribute("a", "a");
        request.getSession().getAttribute("a");
        request.getSession().invalidate();
        logger.info("TestServlet doPost() end......");
    }
}
