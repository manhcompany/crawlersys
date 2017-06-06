package reishi.crawler;

import org.jsoup.nodes.Document;
import reishi.messages.RedisBackupMessage;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

/**
 *
 */
public class NYTimesCrawler implements Crawler {
    private Document doc;
    private static CrawlerConfig config;

    @Override
    public Long getDateTime() {
        return 0L;
    }

    @Override
    public HashSet<String> getUrls() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getContent() {
        return null;
    }

    @Override
    public CrawlerResult crawler() throws IOException {
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

    @Override
    public RedisBackupMessage getCacheBackup() {
        return null;
    }
}
