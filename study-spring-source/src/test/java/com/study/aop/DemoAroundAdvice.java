package com.study.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作用描述：Spring AOP 自定义环绕通知，需实现 {@link MethodInterceptor} 接口。
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2020年08月09日
 */
public class DemoAroundAdvice implements MethodInterceptor {
    protected final Log log = LogFactory.getLog(DemoAroundAdvice.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("Spring Aop 环绕通知 -> 方法执行前：BeforeAdvice");
        log.info("=====>" + invocation.getClass().getName());
        log.info("=====>" + invocation.getMethod().getName());
        Object proceed = invocation.proceed();
        log.info("=====>" + proceed);
        log.info("Spring Aop 环绕通知 -> 方法执行后：AfterAdvice");
        return proceed;
    }
}
