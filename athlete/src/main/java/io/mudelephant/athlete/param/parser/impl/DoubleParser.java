package io.mudelephant.athlete.param.parser.impl;

import io.mudelephant.athlete.param.parser.Parser;

public class DoubleParser implements Parser<Double> {

    @Override
    public Double parse(byte[] buffer, Class typeClass) {
        return new Double(new String(buffer));
    }
}
