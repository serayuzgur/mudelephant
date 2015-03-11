package io.mudelephant.athlete.param.parser.impl;

import io.mudelephant.athlete.param.parser.Parser;
import io.mudelephant.core.ObjectMapper;

public class DefaultParser implements Parser {
    @Override
    public Object parse(byte[] buffer, Class typeClass) {
        return ObjectMapper.getInstance().fromJson(buffer, typeClass);
    }
}
