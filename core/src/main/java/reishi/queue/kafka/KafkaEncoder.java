package reishi.queue.kafka;

import kafka.serializer.Encoder;
import org.apache.kafka.common.serialization.Serializer;
import reishi.queue.messages.KafkaMessage;
import reishi.utils.Serialize;

import java.io.IOException;
import java.util.Map;

/**
 * Created by manhtt on 15/03/2017.
 */
public class KafkaEncoder implements Encoder<KafkaMessage>, Serializer<KafkaMessage> {
    @Override
    public byte[] toBytes(KafkaMessage kafkaMessage) {
        try {
            return Serialize.serialize(kafkaMessage);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, KafkaMessage kafkaMessage) {
        return toBytes(kafkaMessage);
    }

    @Override
    public void close() {

    }
}
