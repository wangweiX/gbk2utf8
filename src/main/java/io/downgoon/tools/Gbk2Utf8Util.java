package io.downgoon.tools;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 编码转换工具集
 * convert GBK encoding to UTF-8
 *
 * @author wangwei
 * @date 2018/05/18
 */
@Slf4j
public class Gbk2Utf8Util {

    /**
     * GBK 转化为 UTF-8
     *
     * @param srcPath
     * @param dstPath
     */
    public static void convert(String srcPath, String dstPath, String[] extensions) {
        if (StringUtils.isBlank(srcPath) || StringUtils.isBlank(dstPath)) {
            log.error("Usage: Gbk2Utf8 <src-gbk-path> <dst-utf8-path> [include-extension (default 'java')]");
            System.exit(1);
        }

        if (ArrayUtils.isEmpty(extensions)) {
            log.error("");
            System.exit(1);
        }

        Collection<File> gbkFiles = FileUtils.listFiles(new File(srcPath), extensions, true);
        int count = 0;
        for (File gbkFile : gbkFiles) {

            AtomicBoolean isGBK = new AtomicBoolean();
            String srcEncoding = encodingName(gbkFile, isGBK);

            // is NOT GBK/GB-2312/GB18030
            if (!isGBK.get()) {
                log.info("skip " + srcEncoding + " on " + gbkFile.getName());
                continue;
            }

            String subPathFile = gbkFile.getAbsolutePath().substring(srcPath.length());
            String utf8FileName = dstPath + subPathFile;
            log.info("converting NO." + (++count) + " " + srcEncoding + " on " + subPathFile.substring(1));

            try {
                FileUtils.writeLines(new File(utf8FileName), "UTF-8", FileUtils.readLines(gbkFile, "GBK"));
            } catch (IOException e) {
                log.error("Fail to convert file :" + subPathFile, e);
            }
        }
    }

    private static EncodingDetect detect = new EncodingDetect();

    /**
     * @param isGBK output param indicating whether the file is GBK or NOT
     */
    private static String encodingName(File file, AtomicBoolean isGBK) {
        int encodingNumber = detect.detectEncoding(file);
        // may return GBK > GB-2312 > GB18030
        String name = EncodingDetect.javaname[encodingNumber];
        isGBK.set(name.startsWith("GB"));
        return name;
    }
}
