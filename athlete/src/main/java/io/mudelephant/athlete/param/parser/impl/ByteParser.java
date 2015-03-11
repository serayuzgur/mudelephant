package io.mudelephant.athlete.param.parser.impl;

import io.mudelephant.athlete.param.parser.Parser;

public class ByteParser implements Parser<Byte> {

    @Override
    public Byte parse(byte[] buffer, Class typeClass) {
        return buffer[0];
    }
}
