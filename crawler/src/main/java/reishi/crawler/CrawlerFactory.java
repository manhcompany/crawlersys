package reishi.crawler;

/**
 *
 */
public class CrawlerFactory {
    public static Crawler getCrawler(CrawlerDomain crawlerDomain, String url) {
        if(crawlerDomain.equals(CrawlerDomain.KINHDOANH_VNEXPRESS)) {
            return new VnExpressCrawler(url);
        } else {
            return new UnknowCrawler();
        }
    }
}
