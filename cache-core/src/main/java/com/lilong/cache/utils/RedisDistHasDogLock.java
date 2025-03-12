package com.lilong.cache.utils;

import com.lilong.cache.commons.LockItem;
import com.lilong.cache.commons.LockItemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.*;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author : lilong
 * @date : 2025-03-10 23:12
 * @description : 分布式锁包含看门狗
 */
@Service
@Slf4j
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
    private final static String DELAY_LOCK_LUA =
            "if redis.call('get',KEYS[1])==ARGV[1] then\n" +
                    "        return redis.call('pexpire', KEYS[1],ARGV[2])\n" +
                    "    else return 0 end";
    /*还有并发问题，考虑ThreadLocal*/
    /**
     * 线程标识
     */
    private ThreadLocal<String> lockerId = new ThreadLocal<>();
    /**
     * 拥有线程
     */
    private Thread ownerThread;
    /**
     * 看门狗线程
     */
    private Thread watchDogThread;
    private static DelayQueue<LockItemVo<LockItem>> delayDog = new DelayQueue<>();

    /**
     * 加锁
     */
    @Override
    public void lock() {
        while (!tryLock()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                log.error("线程异常:{}", e);
            }
        }
    }

    /**
     * 尝试加锁
     */
    @Override
    public boolean tryLock() {
        Thread thread = Thread.currentThread();
        if (ownerThread == thread) {
            return true;
        } else if (ownerThread != null) {
            return false;
        }
        Jedis resource = jedisPool.getResource();
        try {
            String lockId = UUID.randomUUID().toString();
            SetParams params = new SetParams();
            //加锁时间1s
            params.px(LOCK_TIME);
            params.nx();
            synchronized (this) {
                if (ownerThread == null
                        && "OK".equals(resource.set(RS_DISTLOCK_NS + lockName, lockId, params))) {
                    lockerId.set(lockId);
                    ownerThread = thread;
                    //  设置看门狗
                    if (watchDogThread == null) {
                        watchDogThread = new Thread(new ExpireWatchDogTask(), "watchDog");
                        watchDogThread.setDaemon(true);
                        watchDogThread.start();
                    }
                    //往延迟阻塞队列中加入元素（让看门口可以在过期之前一点点的时间去做锁的续期）
                    delayDog.add(new LockItemVo<>((int) LOCK_TIME, new LockItem(lockName, lockId)));
                    return true;
                }
                return false;
            }
        } finally {
            resource.close();
        }
    }

    private class ExpireWatchDogTask implements Runnable {
        @Override
        public void run() {
            log.info("delayed dog is running");
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    LockItem lockItem = delayDog.take().getData();//只有元素快到期了才能take到  0.9s
                    Jedis jedis = null;
                    try {
                        jedis = jedisPool.getResource();
                        Long result = (Long) jedis.eval(DELAY_LOCK_LUA,
                                Arrays.asList(RS_DISTLOCK_NS + lockItem.getKey()),
                                Arrays.asList(lockItem.getValue(), LOCK_TIME_STR));
                        if (result.longValue() == 0L) {
                            System.out.println("Redis上的锁已释放，无需续期！");
                        } else {
                            delayDog.add(new LockItemVo<>((int) LOCK_TIME,
                                    new LockItem(lockItem.getKey(), lockItem.getValue())));
                            log.info("Redis上的锁已续期:{}", LOCK_TIME);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("锁续期失败！", e);
                    } finally {
                        if (jedis != null) jedis.close();
                    }
                } catch (InterruptedException e) {
                    log.info("看门狗线程被中断");
                    break;
                }
            }
            log.info("看门狗线程准备关闭......");
        }
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        Thread thread = Thread.currentThread();
        if (thread != ownerThread) {
            throw new UnsupportedOperationException("不允许释放锁！");
        }
        Jedis resource = jedisPool.getResource();
        try {
            Long result = (Long) resource.eval(RELEASE_LOCK_LUA,
                    Collections.singletonList(RS_DISTLOCK_NS + lockName),
                    Collections.singletonList(lockerId.get()));
            if (result != 0) {
                System.out.println("分布式锁已经释放");
            } else {
                System.out.println("分布式锁释放失败");
            }
        } finally {
            if (resource != null) resource.close();
            lockerId.remove();
            ownerThread = null;
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
