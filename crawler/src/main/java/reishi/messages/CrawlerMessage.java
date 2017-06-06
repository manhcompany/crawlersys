package reishi.messages;

import reishi.crawler.*;
import reishi.queue.messages.KafkaMessage;
import reishi.crawler.CrawlerDomain;

import java.io.IOException;
import java.io.Serializable;

/**
 *
 */
public class CrawlerMessage implements CrawlerQueueMessage<CrawlerResult>, Serializable, KafkaMessage {
    private CrawlerDomain domain;
    private String url;
    private Crawler crawler;

    public CrawlerMessage(CrawlerDomain domain, String url) {
        this.domain = domain;
        this.url = url;
    }

    @Override
    public CrawlerResult crawler() throws IOException {
        System.out.println(this.url);
        crawler = CrawlerFactory.getCrawler(domain, url);
        if(!crawler.isCrawled()) {
            return crawler.crawler();
        }
        return new CrawlerResultWithContent();
    }

    public CrawlerDomain getDomain() {
        return domain;
    }

    @Override
    public RedisBackupMessage getCacheBackup() {
        return crawler.getCacheBackup();
    }

    public String getUrl() {
        return url;
    }
}
