package reishi.queue;

import kafka.serializer.Decoder;
import org.apache.kafka.common.serialization.Deserializer;
import reishi.messages.KafkaMessage;
import reishi.utils.Serialize;

import java.io.IOException;
import java.util.Map;

/**
 * Created by manhtt on 15/03/2017.
 */
public class KafkaDecoder implements Decoder<KafkaMessage>, Deserializer {
    @Override
    public KafkaMessage fromBytes(byte[] bytes) {
        try {
            return (KafkaMessage) Serialize.deserialize(bytes);
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public KafkaMessage deserialize(String s, byte[] bytes) {
        return fromBytes(bytes);
    }

    @Override
    public void close() {

    }
}
