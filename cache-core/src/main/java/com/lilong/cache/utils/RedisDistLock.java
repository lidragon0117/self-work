package com.lilong.cache.utils;

import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author : lilong
 * @date : 2025-03-10 22:23
 * @description : 基于redis实现的分布式锁
 */
@Service
public class RedisDistLock implements Lock {
    @Autowired
    private JedisPool jedisPool;
    private final static int LOCK_TIME = 5 * 1000;
    private final static String RS_DISTLOCK_NS = "tdln:";

    private final static String RELEASE_LOCK_LUA =
            "if redis.call('get',KEYS[1])==ARGV[1] then\n" +
                    "        return redis.call('del', KEYS[1])\n" +
                    "    else return 0 end";
    /**
     * 保存每个线程的独有的ID值
     */
    private ThreadLocal<String> lockerId = new ThreadLocal<>();
    /**
     * 解决锁的重入 持有锁的线程
     */
    private Thread ownerThread;
    /**
     * 锁名字
     */
    private String lockName = "lock";

    /**
     * 加锁
     */
    @Override
    public void lock() {
        if (!this.tryLock()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 释放锁
     */
    @Override
    public void unlock() {
        Thread thread = Thread.currentThread();
        if (ownerThread != thread) {
            throw new UnsupportedOperationException("不允许释放锁！");
        }
        Jedis resource = jedisPool.getResource();
        try {
            Long eval = (Long) resource.eval(RELEASE_LOCK_LUA, Arrays.asList(RS_DISTLOCK_NS + lockName), Arrays.asList(lockerId.get()));
            if (eval != 0) {
                System.out.println("分布式锁已经释放");
            } else {
                System.out.println("分布式锁释放失败");
            }
        } finally {
            if (resource != null) {
                resource.close();
            }
            lockerId.remove();
            setOwnerThread(null);
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new UnsupportedOperationException("不支持可中断获取锁！");
    }

    @Override
    public boolean tryLock() {
        Thread thread = Thread.currentThread();
        // 该线程已持有锁
        if (ownerThread == thread) {
            return true;
            // 该锁已经被其他线程持有
        } else if (ownerThread != null) {
            return false;
        }
        Jedis resource = jedisPool.getResource();
        try {
            String id = UUID.randomUUID().toString();
            SetParams params = new SetParams();
            params.px(LOCK_TIME);
            params.nx();
            synchronized (this) {
                // 加锁成功
                if (ownerThread == null && "OK".equals(resource.set(RS_DISTLOCK_NS + lockName, id, params))) {
                    lockerId.set(id);
                    setOwnerThread(thread);
                    return true;
                }
                return false;
            }
        } finally {
            resource.close();
        }
    }

    /**
     * 加锁
     *
     * @param time the maximum time to wait for the lock
     * @param unit the time unit of the {@code time} argument
     * @return
     * @throws InterruptedException
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }


    @Override
    public Condition newCondition() {
        return null;
    }
    public String getLockName() {
        return lockName;
    }

    public void setLockName(String lockName) {
        this.lockName = lockName;
    }

    public Thread getOwnerThread() {
        return ownerThread;
    }

    public void setOwnerThread(Thread ownerThread) {
        this.ownerThread = ownerThread;
    }

}
