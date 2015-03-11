package io.mudelephant.athlete.param.setter;

import io.mudelephant.athlete.param.ExchangeBag;
import io.undertow.util.HeaderValues;

import javax.ws.rs.DefaultValue;

/**
 * ByHeader is a helper class to set a parameter from headers.
 */
public class ByHeader extends Setter {
    String key;

    public ByHeader(DefaultValue defaultValueAnn, Class typeClass, String key) {
        super(defaultValueAnn, typeClass);
        this.key = key;
    }

    /**
     * Gets the header with the given key. Returns the first value for that header.
     *
     * @param bag
     * @return
     */
    byte[] prepare(ExchangeBag bag) {
        HeaderValues value = bag.getExchange().getRequestHeaders().get(key);
        if (value == null)
            return null;
        return value.getFirst().getBytes();
    }

}
