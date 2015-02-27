package io.mudelephant.athlete;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * A default application implementation.
 */
public class DefaultJSR311Application extends Application {
    private final Set<Object> singletons;
    private final Set<Class<?>> classes;
    public DefaultJSR311Application(Set<Object> singletons, Set<Class<?>> classes) {
        this.singletons = new HashSet<Object>(singletons.size());
        this.classes = new HashSet<Class<?>>(classes.size());
        getClasses().addAll(classes);
        getSingletons().addAll(singletons);
    }

    public Set<Class<?>> getClasses() {
        return classes;
    }

    public Set<Object> getSingletons() {
        return singletons;
    }
}
