package io.mudelephant.athlete.param.parser.impl;

import io.mudelephant.athlete.param.parser.Parser;

import java.math.BigInteger;

public class BigIntegerParser implements Parser<BigInteger> {


    @Override
    public BigInteger parse(byte[] buffer, Class typeClass) {
        return new BigInteger(new String(buffer));
    }
}
