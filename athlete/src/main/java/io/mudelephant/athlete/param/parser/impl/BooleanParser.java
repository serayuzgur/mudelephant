package io.mudelephant.athlete.param.parser.impl;

import io.mudelephant.athlete.param.parser.Parser;

public class BooleanParser implements Parser<Boolean> {

    @Override
    public Boolean parse(byte[] buffer, Class typeClass) {
        return "true".equalsIgnoreCase(new String(buffer));
    }
}
