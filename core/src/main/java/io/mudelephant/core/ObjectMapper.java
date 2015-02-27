package io.mudelephant.core;

import org.boon.json.JsonFactory;

/**
 * A singleton wrappper for the Boon Object Mapper.
 */
public class ObjectMapper {

    private static final org.boon.json.ObjectMapper instance = JsonFactory.createUseAnnotations(true);

    public static org.boon.json.ObjectMapper getInstance() {
        return instance;
    }
}
