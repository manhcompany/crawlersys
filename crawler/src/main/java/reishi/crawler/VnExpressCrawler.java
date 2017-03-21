package reishi.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import reishi.cache.Cache;
import reishi.cache.RedisCache;

import java.io.IOException;
import java.util.*;

/**
 *
 */
public class VnExpressCrawler implements Crawler {
    private static final String START = "http://kinhdoanh.vnexpress.net";
    private static final String CONTAINS = "http://kinhdoanh.vnexpress.net";
    private static final String NOT_CONTAINS = "http://kinhdoanh.vnexpress.net/tel:01292333555,http://kinhdoanh.vnexpress.net/tel:012388801238,http://vnexpress.net/Hon 600.000 m\\u00e1y Mac tr\\u00ean th? gi?i nhi?m malware,http://vnexpress.net/Zune mo+'i ta?i nha.c tu+` ?\\u00e0i FM,http://kenh14.vn,http://fica.vn,https://www.facebook.com/baodantridientu,http://duhoc.dantri.com.vn,http://www,https://www,#box_comment,javascript,www.facebook.com,https://eclick.vn,mailto:,google.com";

    private static final String DIV_TITLE = "div.title_news";
    private static final String DIV_SHORT_INTRO="h3.short_intro";
    private static final String DIV_RELATIVE = "div.relative_new > ul > li > a";
    private static final String DIV_DETAIL = "div.fck_detail";
    private static final String DIV_DATE = "div.block_timer";
    private static final String DIV_BREAKUMB = "ul.list_arrow_breakumb > li";

    private Cache redisCache = new RedisCache();

    private static final CrawlerConfig config = new CrawlerConfig(START, CONTAINS, NOT_CONTAINS);
    private String url;
    private Document doc;

    public VnExpressCrawler() {

    }

    public VnExpressCrawler(String url) {
        this.url = url;
    }

    public Long getDateTime() {
        Elements elements = doc.select(DIV_DATE);
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
        Elements titles = doc.select(DIV_TITLE);
        return titles.get(0).text();
    }

    public String getContent() {
        Elements elements = doc.select(DIV_DETAIL);
        return elements.get(0).text();
    }

    public CrawlerResult crawler() throws IOException {
        String crawlerDoc = HttpGet.sentGet(this.url);
        doc = Jsoup.parse(crawlerDoc);
        HashSet<String> urls = getUrls();
        if(isContentSite()) {
            CrawlerResultWithContent crawlerResultWithContent = new CrawlerResultWithContent();
            crawlerResultWithContent.setUrls(urls);
            crawlerResultWithContent.setTitle(getTitle());
            crawlerResultWithContent.setShortIntro(getShortIntro());
            crawlerResultWithContent.setContent(getContent());
            crawlerResultWithContent.setRelative(getRelative());
            crawlerResultWithContent.setDateTime(getDateTime());
            crawlerResultWithContent.setCategories(getCategories());
            String uuid = UUID.randomUUID().toString();
            crawlerResultWithContent.setUuid(uuid);
            crawlerResultWithContent.setUrl(this.url);
            cacheUrl(uuid);
            return crawlerResultWithContent;
        }
        else {
            CrawlerResultWithoutContent crawlerResultWithoutContent = new CrawlerResultWithoutContent();
            crawlerResultWithoutContent.setUrls(urls);
            return crawlerResultWithoutContent;
        }
    }

    @Override
    public List<String> getCategories() {
        List<String> result = new ArrayList<>();
        Elements elements = doc.select(DIV_BREAKUMB);
        for (Element element : elements) {
            result.add(element.text());
        }
        return result;
    }

    @Override
    public String getShortIntro() {
        Elements intros = doc.select(DIV_SHORT_INTRO);
        return intros.text();
    }

    @Override
    public List<String> getRelative() {
        List<String> result = new ArrayList<>();
        Elements elements = doc.select(DIV_RELATIVE);
        for (Element element : elements) {
            result.add(element.attr("href"));
        }
        return result;
    }

    @Override
    public boolean isContentSite() {
        Elements elements = doc.select(DIV_DETAIL);
        if (elements.size() > 0) {
            return true;
        }
        return false;
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
}