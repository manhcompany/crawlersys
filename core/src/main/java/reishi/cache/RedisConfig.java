package reishi.cache;

/**
 *
 */
public class RedisConfig {
    private static String host = System.getenv("REDIS_HOST") != null ? System.getenv("REDIS_HOST") : "localhost";
    private static int port = System.getenv("REDIS_PORT") != null ? Integer.valueOf(System.getenv("REDIS_PORT")) : 6379;

    public static String getHost() {
        return host;
    }

    public static int getPort() {
        return port;
    }
}
