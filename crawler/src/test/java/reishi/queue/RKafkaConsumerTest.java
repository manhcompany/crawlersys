package reishi.queue;

import junit.framework.TestCase;
import reishi.queue.kafka.RKafkaConsumer;
import reishi.queue.messages.DemoMessage;

import java.io.IOException;

/**
 *
 */
public class RKafkaConsumerTest extends TestCase {
    public void testSubscribe() throws Exception {
        RKafkaConsumer<DemoMessage> rKafkaConsumer = new RKafkaConsumer<DemoMessage>("test_vn1", 0, "2") {
            @Override
            public void handle(DemoMessage message) throws IOException {

            }
        };
        rKafkaConsumer.subscribe();
        assertTrue(true);
    }
}