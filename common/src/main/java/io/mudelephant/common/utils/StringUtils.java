package io.mudelephant.common.utils;

/**
 * StringUtils implements some frequently used sting operations
 */
public class StringUtils {


    /**
     * A fast replace method for Slash character.
     * Replaces N consecutive slash characters with single slash.
     *
     * @param original
     * @return
     */
    public final static String replaceNSlashWith1Slash(String original) {
        char[] orgArr = original.toCharArray();
        char[] modArr = new char[orgArr.length];
        boolean isAfterSlash = false;
        int mi = 0;
        for (int oi = 0; oi < orgArr.length; oi++) {
            char ch = orgArr[oi];
            boolean isSlash = '/' == ch;

            if (!isSlash)
                modArr[mi++] = ch;
            else if (isSlash && !isAfterSlash)
                modArr[mi++] = ch;

            isAfterSlash = isSlash;
        }
        return new String(modArr, 0, mi);
    }

}
