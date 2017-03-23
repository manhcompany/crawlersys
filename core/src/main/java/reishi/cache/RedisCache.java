package cache;

/**
 *
 */
public class RedisCache implements Cache {
    @Override
    public String getUUIDByUrl(String url) {
        return RedisReadClient.getInstance().getJedis().get(RedisUtils.getUUIDByUrl_Key(url));
    }

    @Override
    public void cacheUrl(String url, String uuid) {
        RedisWriteClient.getInstance().getJedis().set(RedisUtils.getUUIDByUrl_Key(url), uuid);
    }

    @Override
    public void cacheUrl(String url, String uuid, int second) {
        cacheUrl(url, uuid);
        RedisWriteClient.getInstance().getJedis().expire(RedisUtils.getUUIDByUrl_Key(url), second);
    }

    @Override
    public void cacheUUID(String uuid, String url) {
        RedisWriteClient.getInstance().getJedis().set(RedisUtils.getUUID_Key(uuid), url);
    }

    @Override
    public void cacheUUID(String uuid, String url, int second) {
        cacheUUID(uuid, url);
        RedisWriteClient.getInstance().getJedis().expire(RedisUtils.getUUID_Key(uuid), second);
    }
}
