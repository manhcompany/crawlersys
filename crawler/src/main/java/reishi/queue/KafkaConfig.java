package reishi.queue;

/**
 * Created by manhtt on 16/03/2017.
 */
public class KafkaConfig {
    private static String kafkaConnection = System.getenv("KAFKA_CONNECTION") == null ? "localhost:9092" : System.getenv("KAFKA_CONNECTION");

    public static String getKafkaConnection() {
        return kafkaConnection;
    }

    public static void setKafkaConnection(String kafkaConnection) {
        KafkaConfig.kafkaConnection = kafkaConnection;
    }
}
