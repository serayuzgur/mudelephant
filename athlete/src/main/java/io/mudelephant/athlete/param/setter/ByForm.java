package io.mudelephant.athlete.param.setter;

import io.mudelephant.athlete.param.ExchangeBag;

import javax.ws.rs.DefaultValue;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

/**
 * ByForm is a helper class to set a parameter from form payload.
 */
public class ByForm<T> extends Setter<T> {
    String key;

    public ByForm(DefaultValue defaultValueAnn, Class<T> typeClass, String key) {
        super(defaultValueAnn, typeClass);
        this.key = key;
    }

    String prepare(ExchangeBag bag) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //cache all form params at first pass.
        if (bag.getFormParameters() == null) {
            String[] formParams = new String(bag.getRequestPayload()).split("&");
            HashMap<String, String> map = new HashMap<String, String>(formParams.length);
            String result = null; // for the param we searched for.
            for (String param : formParams) {
                String[] pair = param.split("=");
                try {
                    map.put(pair[0], URLDecoder.decode(pair[1], "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return bag.getFormParameters().get(key);
    }

}
