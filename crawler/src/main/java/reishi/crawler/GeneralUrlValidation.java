package reishi.crawler;

import reishi.cache.RedisCache;

/**
 *
 */
public class GeneralUrlValidation implements UrlValidation {
    private CrawlerConfig config;

    public GeneralUrlValidation(CrawlerConfig config) {
        this.config = config;
    }

    @Override
    public String validate(String url) {
        url = normalizeUrl(url);
        if (!contains(url)) {
            return null;
        }
        if (!notContains(url)) {
            return null;
        }
        return url;
    }

    @Override
    public String normalizeUrl(String url) {
        if(!url.startsWith("/") && !url.startsWith("http") && !url.startsWith("https") && !url.startsWith("www")) {
            url = "/" + url;
        }

        if(!url.contains(config.getStart())  && !url.startsWith("http") && !url.startsWith("https") && !url.startsWith("www")) {
            url = config.getStart() + url;
        }

        if(url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        return url;
    }

    @Override
    public boolean contains(String url) {
        for (int i = 0; i < config.getContains().length; ++i) {
            if (url.contains(config.getContains()[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean notContains(String url) {
        for (int i = 0; i < config.getNotContains().length; ++i) {
            if (url.contains(config.getNotContains()[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isCrawled(String url) {
        RedisCache redisCache = new RedisCache();
        String uuid = redisCache.getUUIDByUrl(url);
        return (uuid != null && !uuid.isEmpty());
    }
}
