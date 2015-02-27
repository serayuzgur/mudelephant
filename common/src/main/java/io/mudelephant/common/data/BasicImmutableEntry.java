package io.mudelephant.common.data;

import java.util.AbstractMap;
import java.util.Map;

/**
 * A basic implementation for {@link AbstractMap.SimpleImmutableEntry}.
 * Nothing special.
 */
public class BasicImmutableEntry<K,V> extends AbstractMap.SimpleImmutableEntry<K,V>{
    public BasicImmutableEntry(K key, V value) {
        super(key, value);
    }

    public BasicImmutableEntry(Map.Entry<? extends K, ? extends V> entry) {
        super(entry);
    }
}
