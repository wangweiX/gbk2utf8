package io.downgoon.tools;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 程序入口
 *
 * @author wangwei
 * @date 2018/05/18
 */
@Slf4j
public class Main {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: Gbk2Utf8 <src-gbk-path> <dst-utf8-path> [include-extension (default 'java')]");
            System.exit(1);
        }
        String[] extensions = null;
        if (args.length == 3) {
            extensions = StringUtils.split(args[2], ",");
        }
        Gbk2Utf8.convert(args[0], args[1], extensions);
    }
}