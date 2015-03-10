package io.mudelephant.athlete.param.setter;

import io.mudelephant.athlete.param.ExchangeBag;

import javax.ws.rs.DefaultValue;

/**
 * ByCookie is a helper class to set a parameter from cookies.
 */
public class ByJson<T> extends Setter<T> {

    public ByJson(DefaultValue defaultValueAnn, Class<T> typeClass) {
        super(defaultValueAnn, typeClass);
    }

    byte[] prepare(ExchangeBag bag) {
        return bag.getRequestPayload();
    }

}
