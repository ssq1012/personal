package com.example.demo.config;

import java.util.Date;
import java.util.Random;

/**
 * @className: CounterLimit
 * @description: 滑动窗口算法
 * @date: 2022/1/8
 * @author: cakin
 */
public class SlideWindowLimit {
    // 滑动窗口大小
    static final int size = 10;
    // 滑动窗口数组，每移动一个格子，更新对应数据项的值
    static int window[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    // 理解为移动窗口中正在计数的格子
    static int curId = 0;
    // 记录上次统计时间
    static Date lastDate = new Date();
    // 当前窗口计数总和
    static int counter = 0;

    /**
     * 功能描述：模拟一次请求是否限流
     *
     * @return true：限流 false；不限流
     * @author 贝医
     * @date 2022/1/8
     * @description:
     */
    static boolean slideWindowLimit() {
        // 获取当前时间
        Date now = new Date();
        // 当前时间同上次记录时间的间隔，单位为秒
        long time = (now.getTime() - lastDate.getTime()) / 1000;
        // 按照新的移动窗口进行计数
        if (time >= 10) {
            // 当前计数格子的下一个格子将被清掉重写
            curId++;
            curId = curId % size;
            int newCurId = curId;
            // 下一个格子将被清掉，总数据减掉
            counter = counter - window[newCurId];
            // 新格子设置为1
            window[newCurId] = 1;
            // 记录滑动的时间
            lastDate = now;
        } else {
            // 当前计数的格子
            ++window[curId];
        }
        ++counter;
        return counter >= 1000;
    }

    // 测试方法
    public static void main(String[] args) {
        for (; ; ) {
            // 模拟一秒
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Random random = new Random();
            int i = random.nextInt(3);
            // 模拟1秒内请求8次
            if (i == 1) {
                for (int j = 0; j < 8; j++) {
                    if (slideWindowLimit()) {
                        System.out.println("限流了" + counter);
                    } else {
                        System.out.println("没限流" + counter);
                    }
                }
            } else if (i == 2) { // 模拟1秒内请求9次
                for (int j = 0; j < 9; j++) {
                    if (slideWindowLimit()) {
                        System.out.println("限流了" + counter);
                    } else {
                        System.out.println("没限流" + counter);
                    }
                }
            } else { // 模拟1秒内请求10次
                for (int j = 0; j < 10; j++) {
                    if (slideWindowLimit()) {
                        System.out.println("限流了" + counter);
                    } else {
                        System.out.println("没限流" + counter);
                    }
                }
            }
        }
    }
}