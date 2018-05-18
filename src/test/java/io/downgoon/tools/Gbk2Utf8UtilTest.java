package io.downgoon.tools;

import org.junit.Before;
import org.junit.Test;

public class Gbk2Utf8UtilTest {

    private String srcProjPath;
    private String dstProjPath;
    private String[] extensions;

    @Before
    public void setUp() throws Exception {
        srcProjPath = "/Users/wangwei/Documents/001-Work/01-Asiainfo/Projects/res-web/res-web";
        dstProjPath = "/Users/wangwei/Desktop/res-web/";
        extensions = new String[]{"java", "bo", "set", "ds", "vm", "xml", "properties", "jsp", "html", "js", "css", "spl"};
    }

    @Test
    public void convert() {
        Gbk2Utf8Util.convert(srcProjPath, dstProjPath, extensions);
    }

}