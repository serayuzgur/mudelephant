package io.mudelephant.athlete.resource;

import io.mudelephant.athlete.param.setter.Setter;
import io.mudelephant.common.data.BasicImmutableEntry;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Holds class and its related method for pairing by route.
 */
public class MethodEntry extends BasicImmutableEntry<Method, Setter[]>{

    public MethodEntry(Method key, Setter[] value) {
        super(key, value);
    }

    public MethodEntry(Map.Entry<? extends Method, ? extends Setter[]> entry) {
        super(entry);
    }
}
