package com.study.aop;

/**
 * 作用描述：TODO
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2020年08月09日
 */
public interface IDemoAdviceService {
    /**
     * 前置通知测试方法
     *
     * @param param
     * @return
     */
    String demoBefore(String param);

    /**
     * 后置通知测试方法
     *
     * @param param
     * @return
     */
    String demoAfter(String param);

    /**
     * 异常通知测试方法
     *
     * @param param
     * @return
     */
    String demoThrows(String param);

    /**
     * 环绕通知测试方法
     *
     * @param param
     * @return
     */
    String demoAround(String param);
}
