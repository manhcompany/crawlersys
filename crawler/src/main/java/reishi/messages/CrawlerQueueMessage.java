package reishi.messages;

import reishi.crawler.CrawlerDomain;
import reishi.crawler.CrawlerDomain;

import java.io.IOException;

/**
 *
 */
public interface CrawlerQueueMessage<T> {
    T crawler() throws IOException;

    CrawlerDomain getDomain();

    RedisBackupMessage getCacheBackup();
}
