package com.study.aop;

/**
 * 作用描述：TODO
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2020年08月09日
 */
public class IDemoAdviceServiceImpl implements IDemoAdviceService {
    @Override
    public String demoBefore(String param) {
        return "前置通知方法接收到的参数：" + param;
    }

    @Override
    public String demoAfter(String param) {
        return "后置通知方法接收到的参数：" + param;
    }

    @Override
    public String demoThrows(String param) {
        Integer.parseInt("a");
        return "异常通知方法接收到的参数：" + param;
    }

    @Override
    public String demoAround(String param) {
        return "环绕通知方法接收到的参数：" + param;
    }
}
