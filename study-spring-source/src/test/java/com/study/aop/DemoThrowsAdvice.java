package com.study.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;
import java.rmi.RemoteException;

/**
 * 作用描述：Spring AOP 自定义异常通知，需实现 {@link ThrowsAdvice} 接口。
 * 由接口说明可知，该接口中没有任何方法，但实现这个接口的类必须至少实现以下4个方法之一：
 * 1、public void afterThrowing(Exception ex)
 * 2、public void afterThrowing(RemoteException ex)
 * 3、public void afterThrowing(Method method, Object[] args, Object target, Exception ex)
 * 4、public void afterThrowing(Method method, Object[] args, Object target, ServletException ex)
 * 否则程序报 Caused by: java.lang.IllegalArgumentException: At least one handler method must be found in class 异常。
 * 注意：接口中的异常类可以为自己自定义的异常类，方法是通过反射调用。
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2020年08月09日
 */
public class DemoThrowsAdvice implements ThrowsAdvice {
    protected final Log log = LogFactory.getLog(DemoAroundAdvice.class);
    public void afterThrowing(Exception ex) {
        log.error("Spring Aop 异常通知：ThrowsAdvice。异常信息：" + ex);
    }

    public void afterThrowing(RemoteException ex) {
        log.error("Spring Aop 异常通知：ThrowsAdvice。异常信息：" + ex);
    }

    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
        log.error("Spring Aop 异常通知：ThrowsAdvice。异常信息：" + ex);
    }

    /*public void afterThrowing(Method method, Object[] args, Object target, ServletException ex) {
        log.error("Spring Aop 异常通知：ThrowsAdvice。异常信息：" + ex);
    }*/
}
