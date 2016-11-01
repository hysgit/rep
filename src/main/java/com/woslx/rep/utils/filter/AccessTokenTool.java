package com.woslx.rep.utils.filter;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.UUID;

/**
 * Created by hy on 10/28/16.
 */
public class AccessTokenTool {
    private static JedisPool jedisPool = createRedisPool();
    private static String access_token_key = "global_weixin_access_token_key";
    private static String lock_key = "lock_key_weixin_access_token";
    private static Integer ACCESS_TOKEN_EXPIRE = 90 * 60;

    private static String redisHost = "127.0.0.1";

    private static int port = 6379;

    private static int threads = 100;

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(5000);
                        Jedis jedis = jedisPool.getResource();
                        System.out.println("即将删除accesstoken");
                        jedis.del(access_token_key);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        for (int i = 0; i < threads; i++) {
            new Thread(new AccessToken()).start();
        }

        try {
            Thread.sleep(1000000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static class AccessToken implements Runnable {

        @Override
        public void run() {
            while(true) {
                try {
                    getAccessToken();
                    Thread.sleep(300);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getAccessToken() {
        Jedis jedis = jedisPool.getResource();
        String accessToken = null;
        RedisLock redisLock = null;
        try {
            accessToken = jedis.get(access_token_key);
            if (accessToken == null) {
                redisLock = new RedisLock(jedisPool, lock_key);

                    redisLock.lock();
                    accessToken = jedis.get(access_token_key);  //再次判断，是否在等待锁的期间，其他线程已经获取了access_token的值
                    if (accessToken != null) {
                        //System.out.println(Thread.currentThread() + " 获取到了锁，但access_token已经有值，直接返回");
                        return accessToken;
                    }
                    System.out.println(Thread.currentThread() + " 获取到了锁，开始获取access_token");
                    List<String> timeList = jedis.time();       //获取服务器当前时间
                    if (timeList == null) {
                        throw new RuntimeException("cann't get redis server's time!");
                    }
                    jedis.setex(access_token_key, ACCESS_TOKEN_EXPIRE, timeList.get(0));
                    System.out.println(Thread.currentThread() + " 准备释放锁");
                    accessToken =  UUID.randomUUID().toString();

            } else {
                return accessToken;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                if (jedis != null) {
                    jedis.close();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            try {
                if (redisLock != null) {
                    redisLock.unlock();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return accessToken;
    }

    public static JedisPool createRedisPool() {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(10);
        config.setMaxWaitMillis(300000);
        config.setMaxTotal(30000);
        return new JedisPool(config, redisHost, port, 300000);
    }
}
