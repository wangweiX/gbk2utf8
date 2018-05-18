package io.downgoon.tools;

import org.junit.Before;
import org.junit.Test;

public class Gbk2Utf8UtilTest {

    private String srcPath;
    private String dstPath;
    private String[] extensions;

    @Before
    public void setUp() throws Exception {
        srcPath = "/Users/wangwei/Documents/001-Work/01-Asiainfo/Projects/res-web/res-web/html";
        dstPath = "/Users/wangwei/Desktop/res-web/html";
        extensions = new String[]{"java", "bo", "set", "ds", "vm", "xml", "properties", "jsp"};
    }

    @Test
    public void convert() {
        Gbk2Utf8Util.convert(srcPath, dstPath, extensions);
    }

}