package io.mudelephant.athlete;

import com.google.inject.Injector;
import io.mudelephant.athlete.configuration.HasAthleteConfiguration;
import io.mudelephant.athlete.handler.GuicePathHandler;
import io.mudelephant.athlete.handler.ResourcePathHandler;
import io.mudelephant.athlete.handler.listener.ExecuteListener;
import io.mudelephant.athlete.resource.ResourceMapper;
import io.mudelephant.core.Bootstrap;
import io.mudelephant.core.Module;
import io.mudelephant.undertow.UndertowModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * Initialize Athlete module, creates a {@link ResourcePathHandler} and registers it to {@link UndertowModule}
 */
public class AthleteModule<T extends HasAthleteConfiguration> extends Module<T> {

    private final static Logger LOGGER = LoggerFactory.getLogger(AthleteModule.class);
    private final UndertowModule undertow;
    private final Set<Object> singletons;
    private final Set<Class<?>> classes;
    private Injector injector;
    private Set<ExecuteListener> executeListeners = new HashSet<>();


    public AthleteModule(final UndertowModule undertow, final Set<Object> singletons, final Set<Class<?>> classes) {
        this.undertow = undertow;
        this.singletons = singletons;
        this.classes = classes;
    }

    public AthleteModule(final UndertowModule undertow, final Set<Object> singletons, final Set<Class<?>> classes, final Injector injector) {
        this(undertow, singletons, classes);
        this.injector = injector;

    }

    public AthleteModule addExecuteListener(ExecuteListener listener) {
        executeListeners.add(listener);
        return this;
    }

    @Override
    public void run(Bootstrap bootstrap, T configuration) {
        String context = configuration.getAthlete().getContext();
        if (context.equals("")) {
            context = "/";
        }
        LOGGER.info("Context: '{}' Athlete Module Running", context);
        ResourceMapper mapper = new ResourceMapper(context, classes);
        ExecuteListener[] listenerArr = new ExecuteListener[executeListeners.size()];
        executeListeners.toArray(listenerArr);
        if (injector != null) {
            LOGGER.info("Context: '{}' Creating GuicePathHandler", context);
            undertow.registerHandler(context, new GuicePathHandler(context, mapper.getRouteMap(), listenerArr, injector));
        } else {
            LOGGER.info("Context: '{}' Creating ResourcePathHandler", context);
            undertow.registerHandler(context, new ResourcePathHandler(context, mapper.getRouteMap(), listenerArr));
        }
    }

}
