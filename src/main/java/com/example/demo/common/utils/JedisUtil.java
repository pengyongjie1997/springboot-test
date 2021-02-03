package com.example.demo.common.utils;

import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class JedisUtil {
    /**
     * 声明连接池对象，声明成static类型，保证项目在启动时，就加载连接池
     */
    private static JedisPool pool;

    /***
     * Jedis连接池连接的最大值
     */
    private static int maxTotal = 20;

    /***
     * Jedis连接池中最大的空闲连接数
     */
    private static int maxIdel = 20;

    /***
     * Jedis连接池中最小的空闲连接数
     */
    private static int miniIdel = 10;

    /**
     * IP地址
     */
    private static String ip = "127.0.0.1";

    /**
     * 端口号
     */
    private static int port = 6379;

    /**
     * 初始化Jedis连接池
     */
    public static void initJedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdel);
        config.setMinIdle(miniIdel);
        //创建连接池，超时时间为2秒
        pool = new JedisPool(config, ip, port, 2000);
    }

    /**
     * 静态代码块初始化连接池
     */
    static {
        initJedisPool();
    }

    /**
     * 从连接池中获取Jedis连接
     *
     * @return jedis连接
     */
    public static Jedis getJedis() {
        return pool.getResource();
    }


    /**
     * 回收jedis连接
     */
    public static void returnJedis(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }
    }


    /***
     * 获取锁
     */
    public static boolean getLock(String key, String value, String nxxx, String expx, long time) {

        Jedis jedis = null;
        try {
            jedis = getJedis();
            String result = jedis.set(key, value, nxxx, expx, time);
            if (result != null && "OK".equals(result))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnJedis(jedis);
        }
        return false;
    }

    /***
     * 解除锁
     */
    public static void unLock(List<String> key, List<String> value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            jedis.eval(script, key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnJedis(jedis);
        }
    }

    /***
     * 设置key
     */
    public static void set(String key,String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.set(key,value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnJedis(jedis);
        }
    }

    /***
     * 获取String类型的值
     */
    public static String get(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnJedis(jedis);
        }
        return null;
    }

    /***
     * 删除key
     * @return
     */
    public static Long del(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnJedis(jedis);
        }
        return null;
    }

}
