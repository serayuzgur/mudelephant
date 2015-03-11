package io.mudelephant.athlete.param.parser.impl;

import io.mudelephant.athlete.param.parser.Parser;

public class ShortParser implements Parser<Short> {

    @Override
    public Short parse(byte[] buffer, Class typeClass) {
        return new Short(new String(buffer));
    }
}
