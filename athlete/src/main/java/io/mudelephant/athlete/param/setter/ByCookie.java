package io.mudelephant.athlete.param.setter;

import io.mudelephant.athlete.param.ExchangeBag;
import io.undertow.server.handlers.Cookie;

import javax.ws.rs.DefaultValue;

/**
 * ByCookie is a helper class to set a parameter from cookies.
 */
public class ByCookie extends Setter {
    String key;

    public ByCookie(DefaultValue defaultValueAnn, Class typeClass, String key) {
        super(defaultValueAnn, typeClass);
        this.key = key;
    }

    byte[] prepare(ExchangeBag bag) {
        Cookie value = bag.getExchange().getRequestCookies().get(key);
        if (value == null)
            return null;

        return value.getValue().getBytes();
    }

}
