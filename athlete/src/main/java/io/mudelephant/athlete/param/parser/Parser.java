package io.mudelephant.athlete.param.parser;

/**
 * Parser interface.
 */
public interface Parser<T> {

    public T parse(byte[] buffer, Class typeClass);

}
