package reishi.sparks;

import reishi.queue.messages.FileContent;
import reishi.queue.messages.FileQueueMessage;
import reishi.wordsegment.WordSegmenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Field   |   Type    |   Mean
 * ----------------------------
 * 0       |   String  |   uuid
 * 1       |   Long    |   timestamp
 * 2       |   String  |   title
 * 3       |   String  |   short introduction
 * 4       |   String  |   Relationship url, separate by ','
 * 5       |   String  |   Categories, separate by ','
 * 6       |   String  |   Content
 * 7       |   String  |   Domain
 */
public class RawLog implements Log, FileContent {
    private static final int NUMS_OF_FIELDS = 8;
    private String domain;
    private String uuid;
    private Long timestamp;
    private String title;
    private String shortIntroduction;
    private List<String> relationUrls;
    private List<String> categories;
    private String content;
    private boolean isNull;
    private String contentWordSegmented;
    private String shortIntroductionWordSegmented;
    private String titleWordSegmented;

    public String getDomain() {
        return domain;
    }

    public String getUuid() {
        return uuid;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getTitle() {
        return title;
    }

    public String getShortIntroduction() {
        return shortIntroduction;
    }

    public List<String> getRelationUrls() {
        return relationUrls;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getContent() {
        return content;
    }

    public String getContentWordSegmented() {
        return contentWordSegmented;
    }

    public RawLog(String line) {
        String[] fields = line.split("\\t");
        if(fields.length == NUMS_OF_FIELDS) {
            this.uuid = fields[0];
            this.timestamp = Long.valueOf(fields[1]);
            this.title = fields[2];
            this.shortIntroduction = fields[3];

            if(fields[4].equals("-")) {
                this.relationUrls = new ArrayList<>();
            } else {
                this.relationUrls = new ArrayList<String>(Arrays.asList(fields[4].split(",")));
            }

            if(fields[5].equals("-")) {
                this.categories = new ArrayList<>();
            } else {
                this.categories = new ArrayList<>(Arrays.asList(fields[5].split(",")));
            }

            this.content = fields[6];
            this.domain = fields[7];
        }
        else {
            isNull = true;
        }
    }

    public void wordSegment() {
        this.contentWordSegmented = WordSegmenter.wordSegment(this.content);
        this.shortIntroductionWordSegmented = WordSegmenter.wordSegment(this.shortIntroduction);
        this.titleWordSegmented = WordSegmenter.wordSegment(this.title);

        if (contentWordSegmented == null || contentWordSegmented.isEmpty()) {
            contentWordSegmented = "-";
        }
        if (titleWordSegmented == null || titleWordSegmented.isEmpty()) {
            titleWordSegmented = "-";
        }
        if (shortIntroductionWordSegmented == null || shortIntroductionWordSegmented.isEmpty()) {
            shortIntroductionWordSegmented = "-";
        }
    }

    @Override
    public boolean isNull() {
        return this.isNull;
    }

    @Override
    public String toFileString() {
        return String.format("%s\t%s\t%s\t%s\t%s", this.uuid, this.domain, this.titleWordSegmented, this.shortIntroductionWordSegmented, this.contentWordSegmented);
    }
}
