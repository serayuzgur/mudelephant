package io.mudelephant.athlete.param.setter;

import io.mudelephant.athlete.param.ExchangeBag;
import io.mudelephant.athlete.param.parser.Parser;
import io.mudelephant.athlete.param.parser.Parsers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.DefaultValue;

/**
 * Setter is an abstract class which requires set of methods for Any Setter implementations.
 * Also some implemented methods for common Setter usage. It has minimum memory footprint and runtime execution cost.
 * Aims to do all actions on the constructor, and leave less to parsing.
 */
public abstract class Setter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Setter.class);

    private Object defaultValue = null;
    private Class typeClass = null;
    private boolean isString = false;
    private boolean isChar = false;
    private Parser parser;


    public Setter(DefaultValue defaultValueAnn, Class typeClass) {
        this.typeClass = typeClass;

        parser = Parsers.decide(typeClass);

        if (defaultValueAnn != null) {
            setDefaultValue(parse(defaultValueAnn.value().getBytes()));
        }
    }

    public boolean isString() {
        return isString;
    }

    public boolean isChar() {
        return isChar;
    }

    abstract byte[] prepare(ExchangeBag exchange);

    /**
     * Helps to get value of the parameter.
     * Handles default value if any defined.
     *
     * @param exchange
     * @return
     */
    public final Object get(ExchangeBag exchange) {
        byte[] value = prepare(exchange);
        if (defaultValue != null && value == null)
            return defaultValue;
        else if (value != null)
            return parse(value);
        else
            return null;
    }

    /**
     * Parses json of the value and returns real type instance.
     *
     * @param buffer
     * @return
     */
    private final Object parse(byte[] buffer) {
        return parser.parse(buffer,getTypeClass());
    }


    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Returns the Class of the generic
     *
     * @return
     */
    public Class getTypeClass() {
        return typeClass;
    }

}
