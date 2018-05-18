package one.wangwei.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class EncodingDetectTest {

    public static void main(String argc[]) {
//        if (argc.length == 0) {
//            System.out.println("Usage: EncodingDetect <URL|File> ... [-d]");
//            System.exit(1);
//        }
//        EncodingDetect sinodetector;
//        int result = Encoding.OTHER;
//        int i;
//        sinodetector = new EncodingDetect();
//        for (i = 0; i < argc.length; i++) {
//            if (argc[i].startsWith("http://") == true) {
//                try {
//                    result = sinodetector.detectEncoding(new URL(argc[i]));
//                } catch (Exception e) {
//                    System.err.println("Bad URL " + e.toString());
//                }
//            } else if (argc[i].equals("-d")) {
//                sinodetector.debug = true;
//                continue;
//            } else {
//                result = sinodetector.detectEncoding(new File(argc[i]));
//            }
//            System.out.println(nicename[result]);
//        }
        detectEncoding(argc);

//        File srcFile = new File("/Users/wangwei/Documents/001-Work/01-Asiainfo/Projects/res-web/res-web/config/DES.properties");
//        File dstFile = new File("/Users/wangwei/Desktop/result/DES.properties");
//        try {
//            FileUtils.writeLines(dstFile, "UTF-8", FileUtils.readLines(srcFile, "BIG5"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


    private static Set<String> encodingSet = new HashSet<>();

    // ISO-8859-2, Shift_JIS, ISO-8859-1, ISO-2022-CN, ISO-8859-8, UTF-8, windows-1250, windows-1252, EUC-KR, Big5, EUC-JP, GB18030, IBM866, UTF-16BE
    // [Unicode, GBK, ISO8859_1, EUC-TW, ISO2022JP, UTF-8, BIG5, ASCII, GB2312, EUC_JP, EUC_KR]
    public static void detectEncoding(String[] args) {
        try {
            String srcProjPath = "/Users/wangwei/Documents/001-Work/01-Asiainfo/Projects/res-web";
            Collection<File> gbkFiles = FileUtils.listFiles(new File(srcProjPath), null, true);

            String[] extensions = new String[]{"java", "bo", "set", "ds", "vm", "xml",
                    "properties", "jsp", "html", "js", "css", "spl", "module", "wvm"};
            for (File file : gbkFiles) {
                String fileExtension = FilenameUtils.getExtension(file.getName());

                if (!ArrayUtils.contains(extensions, fileExtension)) {
                    continue;
                }

                log.info("File path=" + file.getAbsolutePath());
//                Charset charset = EncodingDetect.detectCharset(file);
//                if (charset != null) {
//                    encodingSet.add(charset.displayName());
//                    String content = charset.displayName() + "<====>" + file.getAbsolutePath() + "\n";
//                    wirteFile(content, charset.displayName());
//                }

                // ISO-8859-8,

                String encodingName = Gbk2Utf8Util.encodingName(file);
                if (StringUtils.isNoneBlank(encodingName)) {
                    encodingSet.add(encodingName);
                    String content = encodingName + "<====>" + file.getAbsolutePath() + "\n";
                    wirteFile(content, encodingName);
                }
            }

            System.out.println(encodingSet);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void wirteFile(String content, String encodingName) throws Exception {
        String filePath = "/Users/wangwei/Desktop/result/result_1";
        String fileName = filePath + "_" + encodingName + ".txt";
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileUtils.writeStringToFile(file, content, Charset.forName("UTF-8"), true);
    }
}
