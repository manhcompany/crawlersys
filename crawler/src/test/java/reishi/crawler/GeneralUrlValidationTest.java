package reishi.crawler;

import junit.framework.TestCase;

/**
 * Created by manhtt on 19/03/2017.
 */
public class GeneralUrlValidationTest extends TestCase {
    public void testNormalizeUrl() throws Exception {
        GeneralUrlValidation generalUrlValidation = new GeneralUrlValidation(new CrawlerConfig("", "", ""));
        System.out.println(generalUrlValidation.normalizeUrl("abc"));

    }

}