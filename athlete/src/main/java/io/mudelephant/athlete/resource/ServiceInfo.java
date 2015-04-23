package io.mudelephant.athlete.resource;

import io.mudelephant.athlete.param.setter.Setter;
import org.abstractmeta.reflectify.MethodInvoker;
import org.abstractmeta.reflectify.Reflectify;

import java.lang.reflect.Method;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Holds class and its related provider, invoker, method  and parameter setters for pairing with route.
 */
public class ServiceInfo {
    Reflectify.Provider provider;
    MethodInvoker invoker;
    Method method;
    Setter[] setters;

    public ServiceInfo(Reflectify.Provider provider, MethodInvoker invoker,Method method, Setter[] setters) {
        checkNotNull(provider,"Null provider is not allowed.");
        this.provider = provider;
        checkNotNull(invoker,"Null invoker is not allowed.");
        this.invoker = invoker;
        checkNotNull(method,"Null method is not allowed.");
        this.method = method;
        checkNotNull(setters,"Null setters is not allowed.");
        this.setters = setters;
    }

    public Reflectify.Provider getProvider() {
        return provider;
    }

    public MethodInvoker getInvoker() {
        return invoker;
    }

    public Method getMethod() {
        return method;
    }

    public Setter[] getSetters() {
        return setters;
    }

}
