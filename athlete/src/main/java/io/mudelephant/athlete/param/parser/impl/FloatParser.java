package io.mudelephant.athlete.param.parser.impl;

import io.mudelephant.athlete.param.parser.Parser;

public class FloatParser implements Parser<Float> {


    @Override
    public Float parse(byte[] buffer, Class typeClass) {
        return new Float(new String(buffer));
    }
}
