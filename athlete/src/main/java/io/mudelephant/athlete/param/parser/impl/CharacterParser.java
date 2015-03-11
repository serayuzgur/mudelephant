package io.mudelephant.athlete.param.parser.impl;

import io.mudelephant.athlete.param.parser.Parser;

public class CharacterParser implements Parser<Character> {


    @Override
    public Character parse(byte[] buffer, Class typeClass) {
        return new String(buffer).charAt(0);
    }
}
