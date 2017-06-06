package reishi.messages;

import reishi.queue.messages.FileContent;
import reishi.queue.messages.KafkaMessage;

import java.io.Serializable;

/**
 *
 */
public class RedisBackupMessage implements KafkaMessage, Serializable, FileContent {
    private String url;
    private String uuid;

    public RedisBackupMessage(String url, String uuid) {
        this.url = url;
        this.uuid = uuid;
    }

    public String getUrl() {
        return url;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toFileString() {
        return String.format("%s\t%s", uuid, url);
    }
}
