package io.mudelephant.athlete.param.setter;

import io.mudelephant.athlete.param.ExchangeBag;

import javax.ws.rs.DefaultValue;
import java.util.Deque;

/**
 * ByCookie is a helper class to set a parameter from cookies.
 */
public class ByQuery<T> extends Setter<T>{
    String key;

    public ByQuery(DefaultValue defaultValueAnn, Class<T> typeClass, String key) {
        super(defaultValueAnn,typeClass);
        this.key = key;
    }

    byte[] prepare(ExchangeBag bag) {
        Deque value = bag.getExchange().getQueryParameters().get(key);
        if(value == null)
            return  null;
        //Always String
        return (byte[]) ((String)value.getFirst()).getBytes();
    }

}
