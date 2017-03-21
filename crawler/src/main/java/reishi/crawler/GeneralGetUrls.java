package reishi.crawler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;

/**
 *
 */
public class GeneralGetUrls implements GetUrls {
    private CrawlerConfig config;
    private UrlValidation urlValidation;

    public GeneralGetUrls(CrawlerConfig config) {
        this.config = config;
        urlValidation = new GeneralUrlValidation(config);
    }

    @Override
    public HashSet<String> getUrl(Document document) {
        HashSet<String> result = new HashSet<String>();
        Elements links = document.select("a[href]");
        for (Element link : links) {
            String href = link.attr("href");
            href = urlValidation.validate(href);
            if(href != null) {
                result.add(href);
            }
        }
        return result;
    }

    @Override
    public String verify(String href) {
        return urlValidation.validate(href);
    }
}
