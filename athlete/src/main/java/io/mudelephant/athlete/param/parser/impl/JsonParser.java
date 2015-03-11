package io.mudelephant.athlete.param.parser.impl;

import io.mudelephant.athlete.param.parser.Parser;
import io.mudelephant.core.ObjectMapper;

public class JsonParser implements Parser {
    @Override
    public Object parse(byte[] buffer, Class typeClass) {
        //TODO: Convert to parser TLV
        return ObjectMapper.getInstance().fromJson(buffer, typeClass);
    }
}
