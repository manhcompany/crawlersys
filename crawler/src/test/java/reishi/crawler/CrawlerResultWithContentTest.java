package reishi.crawler;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CrawlerResultWithContentTest extends TestCase {
    public void testToFileString() throws Exception {
        CrawlerResultWithContent crawlerResultWithContent = new CrawlerResultWithContent();
        crawlerResultWithContent.setUuid("192012-12-132-21424-113");
        crawlerResultWithContent.setUrl("http://vnexpress.net/tin-tuc/phap-luat/doan-xe-canh-sat-ruot-duoi-ban-lat-oto-cho-ma-tuy-3557434.html");
        crawlerResultWithContent.setContent(" Gần 22h ngày 18/3 trên quốc lộ 6 theo hướng Hòa Bình - Hà Nội, chiếc Fortuner dù bánh phải phía sau bị bung lốp nhưng tài xế vẫn cố phóng nhanh với tốc độ cao. Phía sau 4 ôtô của cảnh sát và một xe máy chuyên dụng bám đuổi sát sao.\n" +
                "\n" +
                "\"Sau chừng 5km, xe công an chắn ngang đường, xe 7 chỗ phanh gấp và bị lật nghiêng. Tài xế và một số người vừa bỏ chạy vừa chống lại cảnh sát, như phim hành động\", một nhân chứng cho hay.");
        crawlerResultWithContent.setDateTime(1441421511L);
        crawlerResultWithContent.setShortIntro("Hàng chục cảnh sát mặc áo chống đạn đã truy đuổi, nổ súng vào chiếc Fortuner 'lao như điên\" qua trạm thu phí Xuân Mai, Hà Nội.");
        crawlerResultWithContent.setTitle("Đoàn xe cảnh sát rượt đuổi, bắn lật ôtô chở ma tuý");

        List<String> categories = new ArrayList<>();
        categories.add("khoa hoc");
        categories.add("hinh su");
        crawlerResultWithContent.setCategories(categories);

        List<String> relates = new ArrayList<>();
        relates.add("http://vnexpress.net/tin-tuc/phap-luat/canh-sat-no-sung-tran-ap-gan-40-nguoi-mang-sung-hon-chien-3556225.html");
        relates.add("http://vnexpress.net/tin-tuc/phap-luat/canh-sat-no-sung-tran-ap-hon-chien-truoc-vu-truong-3323487.html");
        crawlerResultWithContent.setRelative(relates);

        System.out.println(crawlerResultWithContent.toFileString());
    }
}