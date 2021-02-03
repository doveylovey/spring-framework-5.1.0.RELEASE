package com.study.aop;

import org.aopalliance.aop.Advice;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

/**
 * 作用描述：https://cloud.tencent.com/developer/article/1531317
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2020年08月09日
 */
public class DemoAdviceJavaTests {
    @Test
    public void adviceTest() {
        Advice demoBeforeAdvice = new DemoBeforeAdvice();
        Advice demoAfterReturningAdvice = new DemoAfterReturningAdvice();
        Advice demoThrowsAdvice = new DemoThrowsAdvice();
        Advice demoAroundAdvice = new DemoAroundAdvice();

        ProxyFactory proxyFactory = new ProxyFactory(new IDemoAdviceServiceImpl());
        proxyFactory.addAdvice(demoBeforeAdvice);
        proxyFactory.addAdvice(demoAfterReturningAdvice);
        proxyFactory.addAdvice(demoThrowsAdvice);
        proxyFactory.addAdvice(demoAroundAdvice);

        IDemoAdviceService demoAdviceService = (IDemoAdviceService) proxyFactory.getProxy();
        String javaCode = demoAdviceService.demoAround("Java Code");
        System.out.println(javaCode);
    }
}
