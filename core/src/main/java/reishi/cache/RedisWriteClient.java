package cache;

import redis.clients.jedis.Jedis;

/**
 *
 */
public class RedisWriteClient {
    private static RedisWriteClient instance = null;
    private static Jedis jedis;

    public static RedisWriteClient getInstance() {
        if(instance == null) {
            instance = new RedisWriteClient();
        }
        return instance;
    }

    public RedisWriteClient() {
        jedis = new Jedis(RedisConfig.getHost(), RedisConfig.getPort());
    }

    public static Jedis getJedis() {
        return jedis;
    }
}
