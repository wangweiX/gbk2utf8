package io.downgoon.tools;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 编码转换工具集
 * convert GBK encoding to UTF-8
 *
 * @author wangwei
 * @date 2018/05/18
 */
@Slf4j
public class Gbk2Utf8Util {

    private static AtomicInteger fileCount = new AtomicInteger(0);

    /**
     * 工程文件编码转化
     *
     * @param srcProjPath 源工程绝对路劲
     * @param dstProjPath 目标工程绝对路径
     */
    public static void convert(String srcProjPath, String dstProjPath, String[] extensions) {
        if (StringUtils.isBlank(srcProjPath)) {
            log.error("Please config src project absolute path, eg: /Users/wangwei/Documents/001-Work/01-Asiainfo/Projects/res-web/res-web");
            System.exit(1);
        }

        if (StringUtils.isBlank(dstProjPath)) {
            log.error("Please config dest project absolute path, eg: /Users/wangwei/Desktop/res-web/res-web");
            System.exit(1);
        }

        if (ArrayUtils.isEmpty(extensions)) {
            log.error("Please config a file suffix. e.g: [java,xml,properties,spl,js,css,jsp,bo,set,ds,vm]");
            System.exit(1);
        }

        Collection<File> gbkFiles = FileUtils.listFiles(new File(srcProjPath), null, true);
        for (File srcFile : gbkFiles) {

            String srcFileRelativePath = srcFile.getAbsolutePath().substring(srcProjPath.length());
            String distFileAbsolutePath = dstProjPath + srcFileRelativePath;
            File distFile = new File(distFileAbsolutePath);

            String fileExtension = FilenameUtils.getExtension(srcFile.getName());
            // 如果包含指定的文件类型，则执行编码转换逻辑
            if (ArrayUtils.contains(extensions, fileExtension)) {
                if (isGBK(srcFile)) {
                    convertEncoding(srcFile, distFile);
                }
                // 非GBK的直接拷贝
                else {
                    directCopy(srcFile, distFile);
                }
            }
            // 不包含，则直接拷贝
            else {
                directCopy(srcFile, distFile);
            }
        }
    }

    /**
     * 编码转换
     *
     * @param srcFile
     * @param dstFile
     */
    private static void convertEncoding(File srcFile, File dstFile) {
        try {
            String srcEncoding = encodingName(srcFile);
            log.info("Convert file NO." + fileCount.addAndGet(1) + " " + srcEncoding + " on " + srcFile.getAbsolutePath());
            FileUtils.writeLines(dstFile, "UTF-8", FileUtils.readLines(srcFile, "GBK"));
        } catch (IOException e) {
            log.error("Fail to convert file :" + srcFile.getAbsolutePath(), e);
        }
    }

    /**
     * 直接拷贝
     *
     * @param srcFile 源文件
     * @param dstFile 目标文件
     */
    private static void directCopy(File srcFile, File dstFile) {
        try {
            log.info("Copy file NO." + fileCount.addAndGet(1) + " on " + srcFile.getAbsolutePath());
            FileUtils.copyFile(srcFile, dstFile, false);
        } catch (IOException e) {
            log.error("Fail to copy file:" + srcFile.getAbsolutePath(), e);
        }
    }

    private static EncodingDetect detect = new EncodingDetect();

    /**
     * 是否为GBK文件
     *
     * @param file
     * @return trre(GBK or GB2312 or GB18030)
     */
    private static boolean isGBK(File file) {
        String encodingName = encodingName(file);
        return StringUtils.isNoneBlank(encodingName) && encodingName.startsWith("GB");
    }

    /**
     * 获取文件的编码名称
     *
     * @param file 文件对象
     * @return
     */
    private static String encodingName(File file) {
        try {
            int encodingNumber = detect.detectEncoding(file);
            return EncodingDetect.javaname[encodingNumber];
        } catch (Exception e) {
            log.error("Fail to get file encodingName:" + file.getAbsolutePath());
        }
        return "";
    }

}
