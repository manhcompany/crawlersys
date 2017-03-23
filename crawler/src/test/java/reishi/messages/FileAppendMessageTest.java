package reishi.messages;

import junit.framework.TestCase;
import reishi.crawler.CrawlerDomain;
import reishi.crawler.CrawlerResultWithContent;
import reishi.queue.messages.FileAppendMessage;

/**
 *
 */
public class FileAppendMessageTest extends TestCase {
    public void testBuildFileName() throws Exception {
        FileAppendMessage fileAppendMessage = new FileAppendMessage(CrawlerDomain.KINHDOANH_VNEXPRESS.toString(), new CrawlerResultWithContent());
        System.out.println(fileAppendMessage.buildFileName());
        assertTrue(true);
    }

}