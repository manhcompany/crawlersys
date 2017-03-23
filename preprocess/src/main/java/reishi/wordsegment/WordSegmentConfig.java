package reishi.wordsegment;

/**
 * Created by manhtt on 23/03/2017.
 */
public class WordSegmentConfig {
    private static final String senSegmentModel = System.getenv("SENSEGMENT_MODEL") != null ? System.getenv("SENSEGMENT_MODEL") : "/home/manhtt/Downloads/JVnTextPro-v.2.0/models/jvnsensegmenter";

    private static final String wordSegmentModel = System.getenv("WORDSEGMENT_MODEL") != null ? System.getenv("WORDSEGMENT_MODEL") : "/home/manhtt/Downloads/JVnTextPro-v.2.0/models/jvnsegmenter";

    public static String getWordSegmentModel() {
        return wordSegmentModel;
    }

    public static String getSenSegmentModel() {
        return senSegmentModel;
    }
}
