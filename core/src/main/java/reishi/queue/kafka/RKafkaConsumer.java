package reishi.queue.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 */
public abstract class RKafkaConsumer<T> {
    protected Properties props = new Properties();
    protected KafkaConsumer<String, T> consumer;
    protected String topicname;
    protected String group;
    protected int partitionId = 0;

    public RKafkaConsumer(String topicname, int partitionId, String group) {
        this.group = group;
        this.topicname = topicname;
        this.partitionId = partitionId;
        props.put("bootstrap.servers", KafkaConfig.getKafkaConnection());
        props.put("group.id", this.group);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", KafkaDecoder.class.getName());
        this.consumer = new KafkaConsumer<>(props);
        List<TopicPartition> topicPartitionList = new ArrayList<>();
        topicPartitionList.add(new TopicPartition(this.topicname, partitionId));
        this.consumer.assign(topicPartitionList);
    }

    public void subscribe() {
        System.out.println("Subscribed to topic " + topicname);
        while (true) {
            ConsumerRecords<String, T> records = consumer.poll(100);
            for (ConsumerRecord<String, T> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s, partition = %d \n",
                        record.offset(), record.key(), record.value(), record.partition());
                try {
                    handle(record.value());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public abstract void handle(T message) throws IOException;
}