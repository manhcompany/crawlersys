package reishi.crawler;

/**
 *
 */
public class CrawlerConfig {
    private String[] contains;
    private String[] notContains;
    private String start;

    public CrawlerConfig(String start, String contains, String notContains) {
        this.start = start;
        this.contains = contains.split(",");
        this.notContains = notContains.split(",");
    }

    public String getStart() {
        return start;
    }

    public String[] getContains() {
        return contains;
    }

    public String[] getNotContains() {
        return notContains;
    }
}
