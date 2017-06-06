package reishi.crawler;

/**
 *
 */
public class CrawlerFactory {
    public static Crawler getCrawler(CrawlerDomain crawlerDomain, String url) {
        if(crawlerDomain.equals(CrawlerDomain.KINHDOANH_VNEXPRESS)) {
            return new KinhDoanhVnExpressCrawler(url);
        } else if (crawlerDomain.equals(CrawlerDomain.THETHAO_VNEXPRESS)) {
            return new TheThaoVnExpressCrawler(url);
        }else {
            return new UnknowCrawler();
        }
    }
}
