package reishi.crawler;

/**
 * Created by manhtt on 13/03/2017.
 */
public interface UrlValidation {
    String validate(String url);
    String normalizeUrl(String url);
    boolean contains(String url);
    boolean notContains(String url);
    boolean isCrawled(String url);
}
