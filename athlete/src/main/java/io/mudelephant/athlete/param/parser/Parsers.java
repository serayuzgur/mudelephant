package io.mudelephant.athlete.param.parser;

import io.mudelephant.athlete.param.parser.impl.*;

import java.util.HashMap;

/**
 * ParserDecider is a Parser selector according to class.
 * It is not thread safe and it does not need to be.
 * Single thread will use it at the start of server.
 * Initializes base parsers stores and returns single instance for each.
 *
 */
public class Parsers {
    private static HashMap<String, Parser> map = new HashMap<String, Parser>();
    private static Parser defaultParser = new JsonParser();

    static {
        // Initialize singleton parsers.
        map.put("BigInteger", new BigIntegerParser());

        map.put("Boolean", new BooleanParser());
        map.put("boolean", new BooleanParser());

        map.put("Byte", new ByteParser());
        map.put("byte", new ByteParser());

        map.put("Character", new CharacterParser());
        map.put("char", new CharacterParser());

        map.put("Double", new DoubleParser());
        map.put("double", new DoubleParser());

        map.put("Float", new FloatParser());
        map.put("float", new FloatParser());

        map.put("Integer", new IntegerParser());
        map.put("int", new IntegerParser());

        map.put("Long", new LongParser());
        map.put("long", new LongParser());

        map.put("Short", new ShortParser());
        map.put("short", new ShortParser());

        map.put("String", new StringParser());
    }


    /**
     * Returns parsers. Every parser has only one instance.
     *
     * @param clazz
     * @return
     */
    public static Parser decide(Class clazz) {
        Parser parser = map.get(clazz.getSimpleName());
        if (parser == null)
            return defaultParser;
        else
            return parser;
    }
}
