package cache;

import redis.clients.jedis.Jedis;

/**
 *
 */
public class RedisReadClient {
    private static RedisReadClient instance = null;
    private static Jedis jedis;

    public static RedisReadClient getInstance() {
        if(instance == null) {
            instance = new RedisReadClient();
        }
        return instance;
    }

    public RedisReadClient() {
        jedis = new Jedis(RedisConfig.getHost(), RedisConfig.getPort());
    }

    public static Jedis getJedis() {
        return jedis;
    }
}