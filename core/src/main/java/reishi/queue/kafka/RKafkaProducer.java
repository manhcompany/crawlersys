package reishi.queue.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.Closeable;
import java.io.IOException;
import java.util.Properties;

/**
 *
 */
public class RKafkaProducer<T> implements Closeable {
    private String topicName = "";
    private Properties props;
    private int partition;
    private Producer<String, T> producer;

    public RKafkaProducer(String topicName, int partition) {
        this.topicName = topicName;
        this.partition = partition;
        Properties props = new Properties();
        props.put("bootstrap.servers", KafkaConfig.getKafkaConnection());
        props.put("acks", "all");
        props.put("retries", 5);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", KafkaEncoder.class.getName());

        producer = new KafkaProducer<String, T>(props);
    }

    public void send(String key, T value) {
        producer.send(new ProducerRecord<String, T>(topicName, this.partition, key, value));
        System.out.println("Message sent successfully");
    }

    @Override
    public void close() throws IOException {
        producer.close();
    }
}