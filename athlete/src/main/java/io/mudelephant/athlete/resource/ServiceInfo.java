package io.mudelephant.athlete.resource;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import com.esotericsoftware.reflectasm.MethodAccess;
import io.mudelephant.athlete.param.setter.Setter;

import java.lang.reflect.Method;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Holds class and its related provider, invoker, method  and parameter setters for pairing with route.
 */
public class ServiceInfo {
    ConstructorAccess constructorAccess;
    MethodAccess methodAccess;
    Method method;
    Setter[] setters;

    public ServiceInfo(ConstructorAccess constructorAccess, MethodAccess methodAccess,Method method, Setter[] setters) {
        checkNotNull(constructorAccess,"Null constructorAccess is not allowed.");
        this.constructorAccess = constructorAccess;
        checkNotNull(methodAccess,"Null methodAccess is not allowed.");
        this.methodAccess = methodAccess;
        checkNotNull(method,"Null method is not allowed.");
        this.method = method;
        checkNotNull(setters,"Null setters is not allowed.");
        this.setters = setters;
    }

    public ConstructorAccess getConstructorAccess() {
        return constructorAccess;
    }

    public MethodAccess getMethodAccess() {
        return methodAccess;
    }

    public Method getMethod() {
        return method;
    }

    public Setter[] getSetters() {
        return setters;
    }

}
