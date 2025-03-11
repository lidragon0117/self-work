package com.lilong.cache.utils;

import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @author : lilong
 * @date : 2025-03-10 21:49
 * @description : 单体项目锁
 */
@Service
public class AloneLock implements Lock {
    /**
     * 拥有者
     */
    AtomicReference<Thread> owner = new AtomicReference<>();
    /**
     * 等待队列
     */
    LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();
    /**
     * 加锁
     */
    @Override
    public void lock() {
        while(!owner.compareAndSet(null,Thread.currentThread())){
            waiters.add(Thread.currentThread());
            // 当前线程阻塞
            LockSupport.park();
            waiters.remove(Thread.currentThread());
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    /**
     * 释放锁
     */
    @Override
    public void unlock() {
        if(owner.compareAndSet(Thread.currentThread(),null)){
            for (Object  object:waiters.toArray()){
                Thread  next= (Thread) object;
                // 线程唤醒
                LockSupport.unpark(next);
            }
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
