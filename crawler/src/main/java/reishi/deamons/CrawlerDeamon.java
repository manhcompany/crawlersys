package reishi.deamons;

import reishi.listeners.CrawlerListener;

/**
 *
 */
public class CrawlerDeamon {
    public static void main(String[] args) {
        CrawlerListener crawlerListener = new CrawlerListener();
        crawlerListener.start();
    }
}
