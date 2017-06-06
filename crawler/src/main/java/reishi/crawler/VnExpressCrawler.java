package reishi.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import reishi.cache.Cache;
import reishi.cache.RedisCache;
import reishi.messages.RedisBackupMessage;

import java.io.IOException;
import java.util.*;

/**
 *
 */
public class VnExpressCrawler implements Crawler {
    /**
     * start
     * contains
     * not.contains
     * div.title
     * div.short.intro
     * div.relative
     * div.detail
     * div.date
     * div.breakumb
     * domain
     */
    private Map<String, String> configMap;

    private Cache redisCache = new RedisCache();

    private static CrawlerConfig config;
    private String url;
    private String uuid;
    private Document doc;

    public VnExpressCrawler(String url, String start,
                            String contains, String notContains, String divTitle,
                            String divShortIntro, String divRelative, String divDetail,
                            String divDate, String divBreakumb, CrawlerDomain domain) {
        this.url = url;
        configMap = new HashMap<>();
        configMap.put("start", start);
        configMap.put("contains", contains);
        configMap.put("not.contains", notContains);
        configMap.put("div.title", divTitle);
        configMap.put("div.short.intro", divShortIntro);
        configMap.put("div.relative", divRelative);
        configMap.put("div.detail", divDetail);
        configMap.put("div.date", divDate);
        configMap.put("div.breakumb", divBreakumb);
        configMap.put("domain", domain.toString());
        config = new CrawlerConfig(configMap.get("start"), configMap.get("contains"), configMap.get("not.contains"));
    }

    public Long getDateTime() {
        Elements elements = doc.select(configMap.get("div.date"));
        String raw = elements.get(1).text();
        System.out.println(raw);
        String[] rawSplit = raw.split("\\|");
        String date = rawSplit[0].split(",")[1].trim();
        int day = Integer.valueOf(date.split("\\/")[0]);
        int month = Integer.valueOf(date.split("\\/")[1]);
        int year = Integer.valueOf(date.split("\\/")[2]);

        String time = rawSplit[1].trim().split(" ")[0].trim();
        int hour = Integer.valueOf(time.split(":")[0]);
        int minute = Integer.valueOf(time.split(":")[1]);

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day - 31, hour, minute);
        return calendar.getTimeInMillis();
    }

    public HashSet<String> getUrls() {
        GetUrls getUrls = new GeneralGetUrls(config);
        return getUrls.getUrl(doc);
    }

    public String getTitle() {
        Elements titles = doc.select(configMap.get("div.title"));
        return titles.get(0).text();
    }

    public String getContent() {
        Elements elements = doc.select(configMap.get("div.detail"));
        return elements.get(0).text();
    }

    public CrawlerResult crawler() throws IOException {
        String crawlerDoc = HttpGet.sentGet(this.url);
        doc = Jsoup.parse(crawlerDoc);
        HashSet<String> urls = getUrls();
        if(isContentSite()) {
            try {
                CrawlerResultWithContent crawlerResultWithContent = new CrawlerResultWithContent();
                crawlerResultWithContent.setUrls(urls);
                crawlerResultWithContent.setTitle(getTitle());
                crawlerResultWithContent.setShortIntro(getShortIntro());
                crawlerResultWithContent.setContent(getContent());
                crawlerResultWithContent.setRelative(getRelative());
                crawlerResultWithContent.setDateTime(getDateTime());
                crawlerResultWithContent.setCategories(getCategories());
                uuid = UUID.randomUUID().toString();
                crawlerResultWithContent.setUuid(uuid);
                crawlerResultWithContent.setUrl(this.url);
                crawlerResultWithContent.setDomain((Enum.valueOf(CrawlerDomain.class, configMap.get("domain"))));
                cacheUrl(uuid);
                return crawlerResultWithContent;
            } catch (Exception e) {
                e.printStackTrace();
                return new CrawlerResultWithContent();
            }
        }
        else {
            CrawlerResultWithoutContent crawlerResultWithoutContent = new CrawlerResultWithoutContent();
            crawlerResultWithoutContent.setUrls(urls);
            uuid = UUID.randomUUID().toString();
            cacheUrl(uuid);
            return crawlerResultWithoutContent;
        }
    }

    @Override
    public List<String> getCategories() {
        List<String> result = new ArrayList<>();
        Elements elements = doc.select(configMap.get("div.breakumb"));
        for (Element element : elements) {
            result.add(element.text());
        }
        return result;
    }

    @Override
    public String getShortIntro() {
        Elements intros = doc.select(configMap.get("div.short.intro"));
        return intros.text();
    }

    @Override
    public List<String> getRelative() {
        List<String> result = new ArrayList<>();
        Elements elements = doc.select(configMap.get("div.relative"));
        for (Element element : elements) {
            result.add(element.attr("href"));
        }
        return result;
    }

    @Override
    public boolean isContentSite() {
        try {
            Elements elements = doc.select(configMap.get("div.detail"));
            if (elements.size() > 0) {
                return true;
            }
            return false;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean isCrawled() {
        String uuid = redisCache.getUUIDByUrl(this.url);
        if(uuid != null && !uuid.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public void cacheUrl(String uuid) {
        if(isContentSite()) {
            redisCache.cacheUrl(url, uuid);
            redisCache.cacheUUID(uuid, url);
        } else {
            redisCache.cacheUrl(url, uuid, 3600);
            redisCache.cacheUUID(uuid, url, 3600);
        }
    }

    @Override
    public RedisBackupMessage getCacheBackup() {
        if(isContentSite()) {
            return new RedisBackupMessage(this.url, this.uuid);
        } else {
            return null;
        }
    }
}
