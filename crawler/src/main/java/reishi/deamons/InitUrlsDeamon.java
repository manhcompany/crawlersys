package reishi.deamons;

import reishi.crawler.CrawlerDomain;
import reishi.crawler.CrawlerResult;
import reishi.messages.CrawlerMessage;
import reishi.messages.CrawlerQueueMessage;
import reishi.queue.utils.QueueName;
import reishi.queue.kafka.RKafkaProducer;

import java.io.IOException;

/**
 *
 */
public class InitUrlsDeamon {
    public static void main(String[] args) throws IOException {
        RKafkaProducer<CrawlerQueueMessage<CrawlerResult>> producer = new RKafkaProducer<>(QueueName.CRAWLER_QUEUE, 0);
        CrawlerQueueMessage<CrawlerResult> crawler = new CrawlerMessage(CrawlerDomain.THETHAO_VNEXPRESS, "http://www.thethao.vnexpress.net");
        producer.send("0", crawler);
        producer.close();
    }
}
