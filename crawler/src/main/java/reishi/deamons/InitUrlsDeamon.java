package reishi.deamons;

import reishi.crawler.CrawlerDomain;
import reishi.crawler.CrawlerResult;
import reishi.crawler.CrawlerResultWithContent;
import reishi.messages.CrawlerMessage;
import reishi.messages.CrawlerQueueMessage;
import reishi.queue.QueueName;
import reishi.queue.RKafkaProducer;

import java.io.IOException;

/**
 * Created by manhtt on 19/03/2017.
 */
public class InitUrlsDeamon {
    public static void main(String[] args) throws IOException {
        RKafkaProducer<CrawlerQueueMessage<CrawlerResult>> producer = new RKafkaProducer<>(QueueName.CRAWLER_QUEUE, 0);
        CrawlerQueueMessage<CrawlerResult> crawler = new CrawlerMessage(CrawlerDomain.KINHDOANH_VNEXPRESS, "http://www.kinhdoanh.vnexpress.net");
        producer.send("0", crawler);
        producer.close();
    }
}
