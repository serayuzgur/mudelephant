package io.mudelephant.common.utils;

import org.junit.Test;

public class StringUtilsTest {

    @Test
    public void testReplace2SlashWith1() throws Exception {
        String original = "//some//text/with//double////slash";
        String expected = "/some/text/with/double/slash";
        String modified = StringUtils.replaceNSlashWith1Slash(original);
        System.out.println(modified);
        assert expected.equals(modified);
    }
}