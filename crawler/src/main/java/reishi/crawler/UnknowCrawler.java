package reishi.crawler;

import java.util.HashSet;
import java.util.List;

/**
 *
 */
public class UnknowCrawler implements Crawler {
    public Long getDateTime() {
        return null;
    }

    public HashSet<String> getUrls() {
        return null;
    }

    public String getTitle() {
        return null;
    }

    public String getContent() {
        return null;
    }

    public CrawlerResultWithContent crawler() {
        return null;
    }

    @Override
    public List<String> getCategories() {
        return null;
    }

    @Override
    public String getShortIntro() {
        return null;
    }

    @Override
    public List<String> getRelative() {
        return null;
    }

    @Override
    public boolean isContentSite() {
        return false;
    }

    @Override
    public boolean isCrawled() {
        return false;
    }

    @Override
    public void cacheUrl(String uuid) {

    }
}
