package reishi.messages;

import junit.framework.TestCase;
import reishi.crawler.CrawlerDomain;
import reishi.crawler.CrawlerResultWithContent;

/**
 * Created by manhtt on 18/03/2017.
 */
public class FileAppendMessageTest extends TestCase {
    public void testBuildFileName() throws Exception {
        FileAppendMessage fileAppendMessage = new FileAppendMessage(CrawlerDomain.KINHDOANH_VNEXPRESS, new CrawlerResultWithContent());
        System.out.println(fileAppendMessage.buildFileName());
        assertTrue(true);
    }

}