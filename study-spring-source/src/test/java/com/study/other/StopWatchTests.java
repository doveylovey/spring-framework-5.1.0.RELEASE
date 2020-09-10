package com.study.other;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.util.StopWatch;

/**
 * StopWatch 是位于 org.springframework.util 包下的一个工具类，通过它可方便的对程序部分代码进行计时(ms级别)，适用于同步单线程代码块。
 * StopWatch 优点：
 * 1、Spring 自带工具类，可直接使用
 * 2、代码实现简单，使用更简单
 * 3、统一归纳，展示每项任务耗时与占用总时间的百分比，展示结果直观、性能消耗相对较小，并且最大程度的保证了 start 与 stop 之间的时间记录的准确性
 * 4、可在 start 时直接指定任务名字，从而更加直观的显示记录结果
 * StopWatch 缺点：
 * 1、一个 StopWatch 实例一次只能 start 一个 task，不能同时 start 多个 task，并且在该 task 未 stop 之前不能 start 一个新的 task，必须在该 task stop 之后才能 start 新的 task，若要一次 start 多个 task，则需要 new 不同的 StopWatch 实例
 * 2、代码侵入式使用，需要改动多处代码
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2020年09月10日
 */
public class StopWatchTests {
    protected static final Log log = LogFactory.getLog(StopWatchTests.class);

    @Test
    public void traditionTest01() throws InterruptedException {
        long start1 = System.currentTimeMillis();
        // do something
        Thread.sleep(100);
        long end1 = System.currentTimeMillis();
        long start2 = System.currentTimeMillis();
        // do something
        Thread.sleep(200);
        long end2 = System.currentTimeMillis();
        log.info("任务1执行耗时：" + (end1 - start1));
        System.out.println("任务1执行耗时：" + (end1 - start1));
        log.info("任务2执行耗时：" + (end2 - start2));
        System.out.println("任务2执行耗时：" + (end2 - start2));
    }

    @Test
    public void stopWatchTest01() throws InterruptedException {
        // StopWatch 是位于org.springframework.util 包下的一个工具类，通过它可方便的对程序部分代码进行计时(ms级别)，
        // 适用于同步单线程代码块。其中的 start() 方法开始记录、stop() 方法停止记录、prettyPrint() 方法可以直观的输出
        // 代码执行耗时及执行时间百分比、shortSummary() 和 getTotalTimeMillis() 方法用于查看程序执行时间
        StopWatch sw = new StopWatch("Spring 计时器：StopWatch");
        sw.start("task1");
        // do something
        Thread.sleep(100);
        sw.stop();
        sw.start("task2");
        // do something
        Thread.sleep(200);
        sw.stop();
        System.out.println("【调用 prettyPrint() 方法结果】\n" + sw.prettyPrint());
        System.out.println("【调用 shortSummary() 方法结果】\n" + sw.shortSummary());
        System.out.println("【调用 getTotalTimeMillis() 方法结果】\n" + sw.getTotalTimeMillis());
    }
}
