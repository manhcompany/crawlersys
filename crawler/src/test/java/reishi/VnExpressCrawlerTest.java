package reishi;

import junit.framework.TestCase;
import reishi.crawler.Crawler;
import reishi.crawler.CrawlerResult;
import reishi.crawler.CrawlerResultWithContent;
import reishi.crawler.VnExpressCrawler;

/**
 *
 */
public class VnExpressCrawlerTest extends TestCase {
    public void testCrawler() throws Exception {
        Crawler crawler = new VnExpressCrawler("http://vnexpress.net/tin-tuc/the-gioi/malaysia-tuyen-bo-nhan-dang-kim-jong-nam-bang-adn-cua-con-3555778.html");
        CrawlerResult result = crawler.crawler();
        for (String url : result.getUrls()) {
            System.out.println(url);
        }

//        System.out.println(result.getTitle());
//        System.out.println(result.getShortIntro());
//        System.out.println(result.getContent());
//        System.out.println(result.getRelative());
//        System.out.println(result.getDateTime());
//        crawler.cacheUrl(result.getUuid());
        assertNotNull(result);
    }
}