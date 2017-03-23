package reishi.queue.messages;

import reishi.queue.messages.KafkaMessage;

import java.io.IOException;

/**
 *
 */
public interface FileQueueMessage extends KafkaMessage {
    void writeToFile(String fileName) throws IOException;
    String buildFileName();
}
