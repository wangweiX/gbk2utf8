package one.wangwei.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.tika.parser.txt.CharsetDetector;
import org.apache.tika.parser.txt.CharsetMatch;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 文件编码工具
 *
 * @author wangwei
 * @date 2018/05/18
 */
@Slf4j
public class EncodingDetect {

    /**
     * 获取文件的编码格式
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static Charset detectCharset(File file) throws Exception {
        byte[] buffer = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        CharsetDetector detector = new CharsetDetector();
        detector.setText(buffer);
        CharsetMatch match = detector.detect();
        if (match != null) {
            try {
                return Charset.forName(match.getName());
            } catch (UnsupportedCharsetException e) {
                log.info("Charset detected as " + match.getName() + " but the JVM does not support this, detection skipped");
            }
        }
        return null;
    }

}