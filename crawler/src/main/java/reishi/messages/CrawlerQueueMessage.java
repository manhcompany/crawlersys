package reishi.messages;

import reishi.crawler.CrawlerDomain;
import reishi.utils.Serialize;

import java.io.IOException;

/**
 *
 */
public interface CrawlerQueueMessage<T> {
    T crawler() throws IOException;

    CrawlerDomain getDomain();
}
