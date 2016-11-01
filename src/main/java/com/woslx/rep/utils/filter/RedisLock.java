package com.woslx.rep.utils.filter;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * Created by hy on 10/26/16.
 */
public class RedisLock {
    private String time;
    private JedisPool pool;
    private String key;

    public RedisLock(JedisPool pool, String key) {
        this.pool = pool;
        this.key = key;
    }

    public  void lock(){
        Jedis jedis = pool.getResource();
        try {
            while (true) {
                List<String> timeList = jedis.time();
                if (timeList == null) {
                    throw new RuntimeException("redis expcetion，failed to get redis server's time");
                }
                Long tm = Long.valueOf(timeList.get(0));

                Long setnx = jedis.setnx(key, tm + "");
                if (setnx.intValue() != 1)       //1 成功
                {
                    //判断是否过期
                    String s = jedis.get(key);
                    if (s != null) {
                        Long lockTm = Long.valueOf(s);
                        if (tm - lockTm > 60) {
                            jedis.del(key);     //锁过期，删除key
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                        throw new RuntimeException(e);
                    }
                } else {
                    time = timeList.get(0);
                    break;//获取到锁
                }
            }
        }
        finally{
            if(jedis!=null)
            {
                jedis.close();
            }
        }

    }

    public void unlock(){
        Jedis jedis = pool.getResource();
        try {
            String s = jedis.get(key);
            if (time.equals(s)) {        //判断锁是否是自己的
                jedis.del(key);
            }
        }
        finally{
            if(jedis!=null)
            {
                jedis.close();
            }
        }
    }
}


