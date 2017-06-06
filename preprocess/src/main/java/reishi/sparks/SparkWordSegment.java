package reishi.sparks;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import reishi.queue.kafka.RKafkaProducer;
import reishi.queue.messages.FileAppendMessage;
import reishi.queue.messages.FileQueueMessage;
import reishi.queue.utils.QueueName;
import java.util.List;

/**
 *
 */
public class SparkWordSegment implements SparkJob {
    private RKafkaProducer<FileQueueMessage> write2FileProducer = new RKafkaProducer<>(QueueName.APPEND_FILE_QUEUE, 0);
    private SparkConf conf = new SparkConf();
    private JavaSparkContext sc = new JavaSparkContext(conf);
    private String filenames;

    public SparkWordSegment(String filenames) {
        this.filenames = filenames;
    }

    @Override
    public void start() {
        JavaRDD<String> textFile = sc.textFile(filenames);
        List<RawLog> rawLogs = textFile.map(new Function<String, RawLog>() {
            @Override
            public RawLog call(String s) throws Exception {
                return new RawLog(s);
            }
        }).filter(new Function<RawLog, Boolean>() {
            @Override
            public Boolean call(RawLog rawLog) throws Exception {
                return !rawLog.isNull();
            }
        }).collect();

        for (RawLog rawLog : rawLogs) {
            rawLog.wordSegment();
            write2FileProducer.send("0", new FileAppendMessage(rawLog.getDomain(), rawLog));
        }
    }
}
