package io.downgoon.tools;

import java.io.File;

/**
 * 报名转化工具
 *
 * @author wangwei
 * @date 2018/05/18
 */
public class PkgRenameUtil {

    static class Replacement {

        private String originalPathFactor;
        private String alternativePathFactor;

        private String originalPackFactor;
        private String alternativePackFactor;

        /**
         * @param String originalPrefix e.g. com.example
         * @param String alternativePrefix e.g. io.downgoon
         */
        public Replacement(String originalPrefix, String alternativePrefix) {
            originalPathFactor = originalPrefix.replace('.', File.separatorChar);
            alternativePathFactor = alternativePrefix.replace('.', File.separatorChar);

            originalPackFactor = originalPrefix.replaceAll("[.]", "\\\\.");
            alternativePackFactor = alternativePrefix;
        }

        public String replacePathName(String originalPath) {
            return originalPath.replaceAll(originalPathFactor, alternativePathFactor);
        }

        public String replaceCodePack(String originalPack) {
            return originalPack.replaceAll(originalPackFactor, alternativePackFactor);
        }

    }

}
