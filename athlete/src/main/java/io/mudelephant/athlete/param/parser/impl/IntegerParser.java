package io.mudelephant.athlete.param.parser.impl;

import io.mudelephant.athlete.param.parser.Parser;

public class IntegerParser implements Parser<Integer> {


    @Override
    public Integer parse(byte[] buffer, Class typeClass) {
        return new Integer(new String(buffer));
    }
}
