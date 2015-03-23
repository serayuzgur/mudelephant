package io.mudelephant.athlete;

import com.google.inject.Injector;
import io.mudelephant.athlete.configuration.HasAthleteConfiguration;
import io.mudelephant.athlete.handler.GuicePathHandler;
import io.mudelephant.athlete.handler.ResourcePathHandler;
import io.mudelephant.athlete.resource.ResourceMapper;
import io.mudelephant.core.Bootstrap;
import io.mudelephant.core.Module;
import io.mudelephant.undertow.UndertowModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Initialized Athlete module, creates a {@link ResourcePathHandler} and registers it to {@link UndertowModule}
 */
public class AthleteModule<T extends HasAthleteConfiguration> extends Module<T> {

    private final static Logger LOGGER = LoggerFactory.getLogger(AthleteModule.class);
    private final UndertowModule undertow;
    private final Set<Object> singletons;
    private final Set<Class<?>> classes;
    private Injector injector;


    public AthleteModule(final UndertowModule undertow, final Set<Object> singletons, final Set<Class<?>> classes) {
        this.undertow = undertow;
        this.singletons = singletons;
        this.classes = classes;
    }

    public AthleteModule(final UndertowModule undertow, final Set<Object> singletons, final Set<Class<?>> classes, final Injector injector) {
        this(undertow, singletons, classes);
        this.injector = injector;

    }

    @Override
    public void run(Bootstrap bootstrap, T configuration) {
        String context = configuration.getAthlete().getContext();
        if (context.equals("")) {
            context = "/";
        }
        LOGGER.info("Context: '{}' Athlete Module Running", context);
        ResourceMapper mapper = new ResourceMapper(context, classes);
        if (injector != null) {
            LOGGER.info("Context: '{}' Creating GuicePathHandler",context);
            undertow.registerHandler(context, new GuicePathHandler(context, mapper.getRouteMap(), injector));
        } else {
            LOGGER.info("Context: '{}' Creating ResourcePathHandler",context);
            undertow.registerHandler(context, new ResourcePathHandler(context, mapper.getRouteMap()));
        }
    }

}
