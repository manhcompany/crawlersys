package reishi.messages;

import java.io.IOException;

/**
 *
 */
public interface FileQueueMessage extends KafkaMessage {
    void writeToFile(String fileName) throws IOException;
    String buildFileName();
}
