package cache;

/**
 *
 */
public interface Cache {
    String getUUIDByUrl(String url);

    void cacheUrl(String url, String uuid);

    void cacheUrl(String url, String uuid, int second);

    void cacheUUID(String uuid, String url);

    void cacheUUID(String uuid, String url, int second);
}
