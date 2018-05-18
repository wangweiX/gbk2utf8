package io.downgoon.tools;

import org.junit.Before;
import org.junit.Test;

public class Gbk2Utf8UtilTest {

    private String srcPath;
    private String dstPath;
    private String[] extensions;

    @Before
    public void setUp() throws Exception {
        srcPath = "";
        dstPath = "";
        extensions = new String[]{""};
    }

    @Test
    public void convert() {
        Gbk2Utf8Util.convert(srcPath, dstPath, extensions);
    }

}