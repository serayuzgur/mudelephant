package io.mudelephant.athlete.param.parser.impl;

import io.mudelephant.athlete.param.parser.Parser;

public class LongParser implements Parser<Long> {


    @Override
    public Long parse(byte[] buffer, Class typeClass) {
        return new Long(new String(buffer));
    }
}
