package reishi.crawler;

import junit.framework.TestCase;

/**
 * Created by manhtt on 25/03/2017.
 */
public class TheThaoVnExpressCrawlerTest extends TestCase {
    public void testCrawler() throws Exception {
        Crawler crawler = new TheThaoVnExpressCrawler("http://thethao.vnexpress.net/tin-tuc/cac-giai-khac/tay-ban-nha-dai-thang-tiep-tuc-cuoc-dua-song-ma-voi-italy-3560582.html");
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