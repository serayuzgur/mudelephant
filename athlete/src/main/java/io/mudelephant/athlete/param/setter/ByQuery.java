package io.mudelephant.athlete.param.setter;

import io.mudelephant.athlete.param.ExchangeBag;

import javax.ws.rs.DefaultValue;
import java.util.Deque;

/**
 * ByCookie is a helper class to set a parameter from cookies.
 */
public class ByQuery extends Setter {
    String key;

    public ByQuery(DefaultValue defaultValueAnn, Class typeClass, String key) {
        super(defaultValueAnn, typeClass);
        this.key = key;
    }

    byte[] prepare(ExchangeBag bag) {
        Deque value = bag.getExchange().getQueryParameters().get(key);
        if (value == null)
            return null;
        //Always String
        String result = (String) value.getFirst();
        return (result).getBytes();
    }

}
