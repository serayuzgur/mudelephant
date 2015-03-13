package io.mudelephant.athlete;

import io.mudelephant.athlete.configuration.HasAthleteConfiguration;
import io.mudelephant.athlete.handler.ResourcePathHandler;
import io.mudelephant.athlete.resource.ResourceMapper;
import io.mudelephant.core.Bootstrap;
import io.mudelephant.core.Module;
import io.mudelephant.undertow.UndertowModule;

import java.util.Set;

/**
 * Initialized Athlete module, creates a {@link ResourcePathHandler} and registers it to {@link UndertowModule}
 */
public class AthleteModule<T extends HasAthleteConfiguration> extends Module<T> {
    private final UndertowModule undertow;
    private final Set<Object> singletons;
    private final Set<Class<?>> classes;

    public AthleteModule(UndertowModule undertow, Set<Object> singletons, Set<Class<?>> classes) {
        this.undertow = undertow;
        this.singletons = singletons;
        this.classes = classes;
    }

    @Override
    public void run(Bootstrap bootstrap, T configuration) {
        String context = configuration.getAthlete().getContext();
        if (context.equals("")) {
            context = "/";
        }
        ResourceMapper mapper = new ResourceMapper(context, classes);
        undertow.registerHandler(context, new ResourcePathHandler(context, mapper.getRouteMap()));

    }

}
