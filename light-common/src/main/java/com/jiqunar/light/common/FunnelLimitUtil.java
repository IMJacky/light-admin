package com.jiqunar.light.common;

/**
 * 漏斗限流算法
 */
public class FunnelLimitUtil {
    /**
     * 漏斗的总容量
     */
    private final int totalCapacity;

    /**
     * 剩余的漏斗容量
     */
    private int remainCapacity;

    /**
     * 每秒钟的漏水量
     */
    private final int leakPerSecond;

    /**
     * 上次漏水的时间（秒）
     */
    private long lastLeakingSecond = System.currentTimeMillis() / 1000;


    public FunnelLimitUtil(int totalCapacity, int leakPerSecond) {
        this.totalCapacity = totalCapacity;
        this.leakPerSecond = leakPerSecond;
        // 初始时漏斗的剩余容量就等于总容量
        this.remainCapacity = totalCapacity;
    }

    public boolean addWater1(int capacity) {
        // 当前漏水的时间（秒）
        long currentLeakingSecond = System.currentTimeMillis() / 1000;
        // 已经漏了的水容量
        long leakedCapacity = (currentLeakingSecond - lastLeakingSecond) * leakPerSecond;
        // 更新上次漏水时间
        lastLeakingSecond = currentLeakingSecond;
        // 时间过长处理，相当于重新初始漏斗
        if (leakedCapacity < 0) {
            remainCapacity = totalCapacity;
        }
        // 剩余容量加上已经漏了的水容量
        remainCapacity += leakedCapacity;
        // 超出了最大限制，重新初始漏斗
        if (remainCapacity > totalCapacity) {
            remainCapacity = totalCapacity;
        }
        if (remainCapacity >= capacity) {
            // 剩余容量足够，返回true，同时减掉新增的容量
            remainCapacity -= capacity;
            return true;
        }
        return false;
    }

    public boolean addWater(int capacity) {
        long currentLeakingSecond = System.currentTimeMillis() / 1000;
        long leakedCapacity = (currentLeakingSecond - lastLeakingSecond) * leakPerSecond;
        lastLeakingSecond = currentLeakingSecond;
        // 时间过长处理，相当于重新初始漏斗
        if (leakedCapacity < 0) {
            remainCapacity = totalCapacity;
        }
        remainCapacity += leakedCapacity;
        if (remainCapacity > totalCapacity) {
            remainCapacity = totalCapacity;
        }
        if (remainCapacity >= capacity) {
            // 剩余容量足够，返回true，同时减掉新增的容量
            remainCapacity -= capacity;
            return true;
        }
        return false;
    }
}
