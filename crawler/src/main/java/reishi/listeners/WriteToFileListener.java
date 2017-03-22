package reishi.listeners;

import reishi.crawler.WriteToFileConfig;
import reishi.messages.FileQueueMessage;
import reishi.queue.QueueName;
import reishi.queue.RKafkaConsumer;

import java.io.IOException;

/**
 * Created by manhtt on 18/03/2017.
 */
public class WriteToFileListener implements Listener {
    private RKafkaConsumer<FileQueueMessage> fileQueueMessageRKafkaConsumer;

    public WriteToFileListener() {
        fileQueueMessageRKafkaConsumer = new RKafkaConsumer<FileQueueMessage>(QueueName.APPEND_FILE_QUEUE, 0, "1") {
            @Override
            public void handle(FileQueueMessage message) throws IOException {
                message.writeToFile(WriteToFileConfig.getPath() + message.buildFileName());
            }
        };
    }

    @Override
    public void start() {
        fileQueueMessageRKafkaConsumer.subscribe();
    }
}