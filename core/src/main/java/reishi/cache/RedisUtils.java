package cache;

/**
 *
 */
public class RedisUtils {
    public static String getUUIDByUrl_Key(String url) {
        return String.format("url:%s", url);
    }

    public static String getUUID_Key(String uuid) {
        return String.format("uuid:%s", uuid);
    }
}
