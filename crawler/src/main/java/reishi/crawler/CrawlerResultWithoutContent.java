package reishi.crawler;

import java.util.HashSet;

/**
 *
 */
public class CrawlerResultWithoutContent implements CrawlerResult {
    private HashSet<String> urls;

    @Override
    public HashSet<String> getUrls() {
        return this.urls;
    }

    @Override
    public boolean withContent() {
        return false;
    }

    public void setUrls(HashSet<String> urls) {
        this.urls = urls;
    }

    @Override
    public String toFileString() {
        return null;
    }
}
