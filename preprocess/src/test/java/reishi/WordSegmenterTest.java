package reishi;

import junit.framework.TestCase;
import reishi.wordsegment.WordSegmenter;

/**
 * Created by manhtt on 23/03/2017.
 */
public class WordSegmenterTest extends TestCase {
    public void testWordSegment() throws Exception {
        System.out.println(WordSegmenter.wordSegment("Vài tháng gần đây, ông Đức - Giám đốc một đơn vị đã có gần 10 năm hoạt động trong lĩnh vực tư vấn, môi giới chuyển nhượng dự án bất động sản tại Hà Nội bận rộn hơn hẳn vì liên tiếp nhận được nhiều đơn đặt hàng mới. Số yêu cầu mua lại dự án tăng gấp đôi so với khoảng một năm về trước. Không ít nhà đầu tư trong số này là các doanh nghiệp \"ngoại đạo\" với bất động sản nhưng giờ muốn lấn sân sang lĩnh vực mới sau khi thị trường địa ốc ghi nhận thanh khoản tốt hơn trong 2 năm qua. Để tư vấn cho các khách hàng, ông liên tục di chuyển từ Bắc vào Nam, tới nhiều tỉnh, thành ven biển miền Trung, từ dự án căn hộ cho đến các khu nghỉ dưỡng."));
    }

}