package com.study.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * 作用描述：Spring AOP 自定义后置通知，需实现 {@link AfterReturningAdvice} 接口。
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2020年08月09日
 */
public class DemoAfterReturningAdvice implements AfterReturningAdvice {
    protected final Log log = LogFactory.getLog(DemoAroundAdvice.class);
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        log.info("Spring Aop 后置通知：AfterReturningAdvice");
    }
}
