package io.mudelephant.athlete.param.setter;

import io.mudelephant.athlete.param.ExchangeBag;
import io.mudelephant.core.ObjectMapper;

import javax.ws.rs.DefaultValue;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

/**
 * Setter is an abstract class which requires set of methods for Any Setter implementations.
 * Also some implemented methods for common Setter usage.
 */
public abstract class Setter<T> {

    T defaultValue = null;
    Class<T> typeClass = null;
    Constructor<T> constructor = null;

    public Setter(DefaultValue defaultValueAnn, Class<T> typeClass) {
        this.typeClass = typeClass;
        try {
            constructor = getTypeClass().getConstructor(String.class);
        } catch (NoSuchMethodException e) {
            //Don't mind life goes on.
        }

        if (defaultValueAnn != null)
            this.defaultValue = parse(defaultValueAnn.value());


    }

    abstract String prepare(ExchangeBag exchange);

    /**
     * Helps to get value of the parameter.
     * Handles default value if any defined.
     *
     * @param exchange
     * @return
     */
    public final T get(ExchangeBag exchange) {
        String value = prepare(exchange);
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
     * @param valueString
     * @return
     */
    private final T parse(String valueString) {
        T value = null;
        if (constructor != null) {
            try {

                Constructor<T> constructor = getTypeClass().getConstructor(String.class);
                value = constructor.newInstance(valueString);
                return value;
            } catch (Exception e) {
                //Just try to parse json. Let it go!
            }
        }
        value = ObjectMapper.getInstance().fromJson(valueString, getTypeClass());
        return value;
    }


    public void setDefaultValue(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Finds the class of the Generic and stores for future usage.
     * Called by constructor.
     */
    private final void decideTypeClass() {
        if (typeClass == null) {
            this.typeClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
    }

    /**
     * Returns the Class of the generic
     *
     * @return
     */
    public Class<T> getTypeClass() {
        return typeClass;
    }
}
