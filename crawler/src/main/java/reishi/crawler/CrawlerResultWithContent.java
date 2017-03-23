package reishi.crawler;

import reishi.queue.messages.FileContent;
import reishi.queue.messages.KafkaMessage;

import java.util.HashSet;
import java.util.List;

/**
 *
 */
public class CrawlerResultWithContent implements KafkaMessage, FileContent, CrawlerResult {
    private String url;
    private String content;
    private Long dateTime;
    private HashSet<String> urls;
    private String title;
    private String shortIntro;
    private List<String> relative;
    private List<String> categories;
    private String uuid;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getRelative() {
        return relative;
    }

    public void setRelative(List<String> relative) {
        this.relative = relative;
    }

    public String getShortIntro() {
        return shortIntro;
    }

    public void setShortIntro(String shortIntro) {
        this.shortIntro = shortIntro;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public HashSet<String> getUrls() {
        return urls;
    }

    @Override
    public boolean withContent() {
        return true;
    }

    public void setUrls(HashSet<String> urls) {
        this.urls = urls;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toFileString() {
        String relativeString = "-";
        if(relative != null && !relative.isEmpty()) {
            relativeString = String.join(",", relative);
        }
        String categoriesString = "-";
        if(categories != null && !categories.isEmpty()) {
             categoriesString = String.join(",", categories);
        }
        try {
            String result = String.format("%s\t%d\t%s\t%s\t%s\t%s\t%s", uuid, dateTime, title, shortIntro.replace("\t", " ").trim(), relativeString, categoriesString, content.replace("\n", " ").trim());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(this.url);
        }

        return "-";
    }
}
