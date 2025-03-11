package com.lilong.cache.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author : lilong
 * @date : 2025-03-10 23:12
 * @description : 分布式锁包含看门狗
 */
@Service
public class RedisDistHasDogLock implements Lock {
    @Autowired
    private JedisPool jedisPool;
    private String lockName = "lock";
    private final static int LOCK_TIME = 1 * 1000;
    private final static String LOCK_TIME_STR = String.valueOf(LOCK_TIME);
    private final static String RS_DISTLOCK_NS = "tdln2:";

    private final static String RELEASE_LOCK_LUA =
            "if redis.call('get',KEYS[1])==ARGV[1] then\n" +
                    "        return redis.call('del', KEYS[1])\n" +
                    "    else return 0 end";
    /*还有并发问题，考虑ThreadLocal*/
    /**
     * 线程标识
     */
    private ThreadLocal<String> lockerId = new ThreadLocal<>();
    /**
     * 拥有线程
     */
    Thread onwerThread;

    /**
     * 加锁
     */
    @Override
    public void lock() {
        while (!tryLock()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    /**
     * 尝试加锁
     */
    @Override
    public boolean tryLock() {
        Thread thread = Thread.currentThread();
        if (onwerThread == thread) {
            return true;
        } else if (onwerThread != null) {
            return false;
        }
        Jedis resource = jedisPool.getResource();
        try {
            //TODO 看门狗
        }finally {

        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
