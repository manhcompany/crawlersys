package reishi.listeners;

import reishi.crawler.CrawlerDomain;
import reishi.crawler.CrawlerResult;
import reishi.crawler.CrawlerResultWithContent;
import reishi.messages.*;
import reishi.queue.QueueName;
import reishi.queue.RKafkaConsumer;
import reishi.queue.RKafkaProducer;

import java.io.IOException;
import java.util.HashSet;

/**
 *
 */
public class CrawlerListener implements Listener{
    private RKafkaConsumer<CrawlerQueueMessage<CrawlerResult>> crawlerConsumer;
    private RKafkaProducer<CrawlerQueueMessage<CrawlerResult>> crawlerProducer = new RKafkaProducer<>(QueueName.CRAWLER_QUEUE, 0);
    private RKafkaProducer<FileQueueMessage> fileProducer = new RKafkaProducer<>(QueueName.APPEND_FILE_QUEUE, 0);

    public CrawlerListener() {
        crawlerConsumer = new RKafkaConsumer<CrawlerQueueMessage<CrawlerResult>>(QueueName.CRAWLER_QUEUE, 0, "0") {
            @Override
            public void handle(CrawlerQueueMessage<CrawlerResult> message) throws IOException {
                CrawlerResult result = message.crawler();
                HashSet<String> urls = result.getUrls();
                System.out.println(message.getDomain());
                // send urls to crawler queue
                if(urls != null && !urls.isEmpty()) {
                    for (String url : urls) {
                        CrawlerDomain crawlerDomain = message.getDomain();
                        CrawlerQueueMessage<CrawlerResult> newCrawler = new CrawlerMessage(crawlerDomain, url);
                        crawlerProducer.send("0", newCrawler);
                    }
                }

                // send result to append file queue
                if(result.withContent() && result.getUrls() != null) {
                    FileQueueMessage fileContent = new FileAppendMessage(message.getDomain(), result);
                    fileProducer.send("0", fileContent);
                }
            }
        };
    }

    @Override
    public void start() {
        crawlerConsumer.subscribe();
    }
}