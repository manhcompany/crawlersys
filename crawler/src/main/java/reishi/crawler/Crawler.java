package reishi.crawler;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

/**
 *
 */
public interface Crawler {
    Long getDateTime();
    HashSet<String> getUrls();
    String getTitle();
    String getContent();
    CrawlerResult crawler() throws IOException;
    List<String> getCategories();
    String getShortIntro();
    List<String> getRelative();
    boolean isContentSite();
    boolean isCrawled();
    void cacheUrl(String uuid);
}
