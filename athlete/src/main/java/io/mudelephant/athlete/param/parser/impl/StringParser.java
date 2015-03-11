package io.mudelephant.athlete.param.parser.impl;

import io.mudelephant.athlete.param.parser.Parser;

public class StringParser implements Parser<String> {

    @Override
    public String parse(byte[] buffer, Class typeClass) {
        return new String(buffer);
    }
}
