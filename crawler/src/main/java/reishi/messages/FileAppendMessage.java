package reishi.messages;

import reishi.crawler.CrawlerDomain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 *
 */
public class FileAppendMessage implements FileQueueMessage {
    private FileContent content;
    private CrawlerDomain domain;

    public FileAppendMessage(CrawlerDomain domain, FileContent content) {
        this.domain = domain;
        this.content = content;
    }

    @Override
    public void writeToFile(String fileName) throws IOException {
        List<String> contents = new ArrayList<>();
        String contenString = content.toFileString();
        if(!contenString.equals("-")) {
            contents.add(contenString);
            Path path = Paths.get(fileName);
            Files.write(path, contents, UTF_8, CREATE, APPEND);
        }
    }

    @Override
    public String buildFileName() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH");
        String sdt = df.format(new Date(System.currentTimeMillis())) + ".tsv";
        return String.format("%s-%s", domain.toString(), sdt);
    }
}
