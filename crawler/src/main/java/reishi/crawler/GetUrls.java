package reishi.crawler;

import org.jsoup.nodes.Document;

import java.util.HashSet;

/**
 *
 */
public interface GetUrls {
    HashSet<String> getUrl(Document document);
    String verify(String url);
}
