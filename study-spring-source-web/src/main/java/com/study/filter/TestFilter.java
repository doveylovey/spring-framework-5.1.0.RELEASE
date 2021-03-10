package com.study.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 加载顺序(从启动日志来看)：
 * 启动的顺序为 listener -> filter -> servlet。简单记为：理(Listener)发(Filter)师(Servlet)
 * 执行的顺序不会因为三个标签在配置文件中的先后顺序而改变。
 *
 * @author Administrator
 */
public class TestFilter implements Filter {
    private final Log logger = LogFactory.getLog(TestFilter.class);

    @Override
    public void destroy() {
        logger.info("TestFilter destroy().......");
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        logger.info("TestFilter doFilter().......");
        arg2.doFilter(arg0, arg1);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        logger.info("TestFilter init().......");
    }
}
