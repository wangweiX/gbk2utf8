package io.downgoon.tools;

import java.io.File;
import java.net.URL;

import static io.downgoon.tools.Encoding.nicename;

public class EncodingDetectTest {

    public static void main(String argc[]) {
        if (argc.length == 0) {
            System.out.println("Usage: EncodingDetect <URL|File> ... [-d]");
            System.exit(1);
        }
        EncodingDetect sinodetector;
        int result = Encoding.OTHER;
        int i;
        sinodetector = new EncodingDetect();
        for (i = 0; i < argc.length; i++) {
            if (argc[i].startsWith("http://") == true) {
                try {
                    result = sinodetector.detectEncoding(new URL(argc[i]));
                } catch (Exception e) {
                    System.err.println("Bad URL " + e.toString());
                }
            } else if (argc[i].equals("-d")) {
                sinodetector.debug = true;
                continue;
            } else {
                result = sinodetector.detectEncoding(new File(argc[i]));
            }
            System.out.println(nicename[result]);
        }
    }
}
