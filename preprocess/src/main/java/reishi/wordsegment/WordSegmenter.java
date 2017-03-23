package reishi.wordsegment;

import jvntextpro.JVnTextPro;

/**
 *
 */
public class WordSegmenter {
    private static JVnTextPro textPro = null;

    public static String wordSegment(String input) {
        if (textPro == null) {
            textPro = new JVnTextPro();
            textPro.initSenSegmenter(WordSegmentConfig.getSenSegmentModel());
            textPro.initSenTokenization();
            textPro.initSegmenter(WordSegmentConfig.getWordSegmentModel());
        }
        return textPro.wordSegment(textPro.senTokenize(textPro.senSegment(input)));
    }
}
