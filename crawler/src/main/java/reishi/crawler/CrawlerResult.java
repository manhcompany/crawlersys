package reishi.crawler;

import reishi.messages.FileContent;

import java.util.HashSet;

/**
 *
 */
public interface CrawlerResult extends FileContent {
    HashSet<String> getUrls();
    boolean withContent();
}
