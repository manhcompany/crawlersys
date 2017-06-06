package reishi.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import reishi.cache.Cache;
import reishi.cache.RedisCache;

import java.io.IOException;
import java.util.*;

/**
 *
 */
public class TheThaoVnExpressCrawler extends VnExpressCrawler {
    private static final String START = "http://thethao.vnexpress.net";
    private static final String CONTAINS = "http://thethao.vnexpress.net";
    private static final String NOT_CONTAINS = "http://thethao.vnexpress.net/tel:01292333555,http://thethao.vnexpress.net/tel:012388801238,http://vnexpress.net/Hon 600.000 m\\u00e1y Mac tr\\u00ean th? gi?i nhi?m malware,http://vnexpress.net/Zune mo+'i ta?i nha.c tu+` ?\\u00e0i FM,http://kenh14.vn,http://fica.vn,https://www.facebook.com/baodantridientu,http://duhoc.dantri.com.vn,http://www,https://www,#box_comment,javascript,www.facebook.com,https://eclick.vn,mailto:,google.com";

    private static final String DIV_TITLE = "div.title_news";
    private static final String DIV_SHORT_INTRO = "h3.short_intro";
    private static final String DIV_RELATIVE = "div.relative_new > ul > li > a";
    private static final String DIV_DETAIL = "div.fck_detail";
    private static final String DIV_DATE = "div.block_timer";
    private static final String DIV_BREAKUMB = "ul.list_arrow_breakumb > li";
    private static final CrawlerDomain domain = CrawlerDomain.THETHAO_VNEXPRESS;

    public TheThaoVnExpressCrawler(String url) {
        super(url, START,
                CONTAINS,
                NOT_CONTAINS,
                DIV_TITLE,
                DIV_SHORT_INTRO,
                DIV_RELATIVE,
                DIV_DETAIL,
                DIV_DATE,
                DIV_BREAKUMB,
                domain);
    }
}
