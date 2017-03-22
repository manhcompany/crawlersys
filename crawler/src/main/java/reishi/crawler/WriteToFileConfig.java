package reishi.crawler;

/**
 * Config for write to file
 */
public class WriteToFileConfig {
    private final static String path = System.getenv("DATA_PATH") != null ? System.getenv("DATA_PATH") : "/home/manhtt/Documents/SourceCode/crawlersys/data/";

    public static String getPath() {
        return path;
    }
}
