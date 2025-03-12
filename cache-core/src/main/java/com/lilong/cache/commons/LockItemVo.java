package com.lilong.cache.commons;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author : lilong
 * @date : 2025-03-12 20:35
 * @description :
 */
public class LockItemVo <T> implements Delayed {
    /*到期时刻  20:00:35,234*/
    private long activeTime;
    /*业务数据，泛型*/
    private T data;
    public long getActiveTime() {
        return activeTime;
    }

    public T getData() {
        return data;
    }
    /**
     * 传入的数值代表过期的时长，单位毫秒，需要乘1000转换为毫秒和到期时间
     *      * 同时提前100毫秒续期,具体的时间可以自己决定
     * @param expirationTime
     * @param data
     */

    public LockItemVo(long expirationTime, T data) {
        super();
        this.activeTime = expirationTime+System.currentTimeMillis()-100;
        this.data = data;
    }
    @Override
    public long getDelay(TimeUnit unit) {
        long d = unit.convert(this.activeTime
                - System.currentTimeMillis(),unit);
        return d;
    }

    @Override
    public int compareTo(Delayed o) {
        long d = (getDelay(TimeUnit.MILLISECONDS)
                -o.getDelay(TimeUnit.MILLISECONDS));
        if (d==0){
            return 0;
        }else{
            if (d<0){
                return -1;
            }else{
                return  1;
            }
        }
    }
}
