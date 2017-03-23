package reishi.queue;

import junit.framework.TestCase;
import reishi.queue.kafka.RKafkaProducer;
import reishi.queue.messages.DemoMessage;

/**
 *
 */
public class RKafkaProducerTest extends TestCase {
    public void testSend() throws Exception {
        RKafkaProducer<DemoMessage> rKafkaProducer = new RKafkaProducer<>("test_vn1", 0);
        for (int i =0; i < 1000; ++i) {
            rKafkaProducer.send("1", new DemoMessage());
        }
    }
}