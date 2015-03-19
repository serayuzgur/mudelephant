package io.mudelephant.athlete.handler;

import com.google.inject.Injector;
import io.mudelephant.athlete.resource.MethodEntry;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Handles guice dependency injection operation for resources.
 */
public class GuicePathHandler extends ResourcePathHandler {

    private Injector injector;

    public GuicePathHandler(final String path, final Map<String, MethodEntry> router, final Injector injector) {
        super(path, router);
        this.injector = injector;
    }

    protected Object createInstance(Method method) throws IllegalAccessException, InstantiationException {
        return injector.getInstance(method.getDeclaringClass());
    }

}
