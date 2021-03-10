package com.study.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * HttpSessionListener 用于监控 session 的创建，销毁等
 * ServletRequestListener 用于监控 servlet 上下文 request
 * ServletRequestAttributeListener 用于监控 request 中的 attribute 的操作
 * <p>
 * 加载顺序(从启动日志来看)：
 * 启动的顺序为 listener -> filter -> servlet。简单记为：理(Listener)发(Filter)师(Servlet)
 * 执行的顺序不会因为三个标签在配置文件中的先后顺序而改变。
 * <p>
 * 作者：jiangmo
 * 链接：https://www.jianshu.com/p/612c3a6673b2
 * 来源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author Administrator
 */
public class TestListener implements HttpSessionListener, ServletRequestListener, ServletRequestAttributeListener {
    private final Log logger = LogFactory.getLog(TestListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent arg) {
        logger.info("TestListener sessionCreated().......");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent arg) {
        logger.info("TestListener sessionDestroyed().......");
    }

    @Override
    public void requestInitialized(ServletRequestEvent arg) {
        logger.info("TestListener requestInitialized()......");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent arg) {
        logger.info("TestListener requestDestroyed()......");
    }

    @Override
    public void attributeAdded(ServletRequestAttributeEvent arg) {
        logger.info("TestListener attributeAdded()......");
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent arg) {
        logger.info("TestListener attributeRemoved()......");
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent arg) {
        logger.info("TestListener attributeReplaced()......");
    }
}
